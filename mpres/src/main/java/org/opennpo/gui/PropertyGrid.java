package org.opennpo.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.EventObject;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableModel;

/**
 *
 * @author Nate Jones
 */
public class PropertyGrid extends JPanel {
    private static Logger log = Logger.getLogger(PropertyGrid.class.getName());
    
    private JTable table;
    private JScrollPane scroll;
    
    public PropertyGrid(){
        setLayout(new BorderLayout());
        scroll = new JScrollPane();
        table = new JTable();
        scroll.setViewportView(table);
        add(scroll);
        scroll.setLocation(0,0);
        scroll.setSize(getSize());
        addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e){
                table.setSize(getWidth(),table.getHeight());
                scroll.setSize(getSize());
            }
        });
    }
    
    public void setSource(Object bean){
        table.setModel(new BeanTableModel(bean));
        table.setCellEditor(new BeanTableEditor());
    }
    
    public static class BeanTableEditor implements TableCellEditor{
        PropertyEditor peditor;
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {
            log.info("entering getTableCellEditorComponent");
            PropertyDescriptor prop = ((BeanTableModel)table.getModel()).getDescriptor(row);
            Class pEditorClass = prop.getPropertyEditorClass();
            System.out.println("GetTableCellEditorComponent: Editor Class = "+pEditorClass);
            Component ret = null;
            if(pEditorClass==null){
                log.info("No property editor for "+row+","+col+" "+prop.getName());
                peditor = null;
            }
            else{
                try {
                    peditor = (PropertyEditor) pEditorClass.newInstance();
                    peditor.setValue(value);
                    log.info(peditor.toString());
                    ret = peditor.getCustomEditor();
                } catch (InstantiationException ex) {
                    Logger.getLogger(PropertyGrid.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(PropertyGrid.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return ret;
        }

        @Override
        public Object getCellEditorValue() {
            if(peditor!=null)
                return peditor.getValue();
            else
                return null;
        }

        @Override
        public boolean isCellEditable(EventObject anEvent) {
            return peditor!=null;
        }

        @Override
        public boolean shouldSelectCell(EventObject anEvent) {
            return peditor!=null;
        }

        @Override
        public boolean stopCellEditing() {
            return true;
        }

        @Override
        public void cancelCellEditing() {}

        @Override
        public void addCellEditorListener(CellEditorListener l) {}

        @Override
        public void removeCellEditorListener(CellEditorListener l) {}
    }
    
    public static class BeanTableModel implements TableModel{
        private BeanInfo info;
        private Object object;
        private List<PropertyDescriptor> properties;
        private boolean showExpert;
        
        public BeanTableModel(Object bean){
            try {
                object = bean;
                info = Introspector.getBeanInfo(bean.getClass());
                properties = Collections.synchronizedList(new Vector<PropertyDescriptor>());
                showExpert = false;
                refreshProperties();
            } catch (IntrospectionException ex) {
                Logger.getLogger(PropertyGrid.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void refreshProperties(){
            for(PropertyDescriptor prop : info.getPropertyDescriptors()){
                if((!prop.isExpert())||showExpert){
                    properties.add(prop);
                }
            }
        }
        
        public boolean isShowExpert(){
            return showExpert;
        }
        
        public void setShowExpert(boolean val){
            showExpert = val;
        }

        @Override
        public int getRowCount() {
            if((info==null)||(info.getPropertyDescriptors()==null)){
                return 0;
            }
            else{
                return properties.size();
            }
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return (columnIndex==0)?"Property":"Value";
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(columnIndex == 0){
                return String.class;
            }
            else{
                return Object.class;
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if(columnIndex==0){
                return false;
            }
            else{
                PropertyDescriptor desc = properties.get(rowIndex);
                return desc.getWriteMethod()!=null;
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            PropertyDescriptor prop = properties.get(rowIndex);
            if(columnIndex ==0){
                return prop.getDisplayName();
            }
            else{
                try {
                    return prop.getReadMethod().invoke(object);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(PropertyGrid.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(PropertyGrid.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(PropertyGrid.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            try {
                PropertyDescriptor prop = properties.get(rowIndex);
                if(aValue instanceof String)
                    prop.getWriteMethod().invoke(object, prop.getValue((String)aValue));
                else
                    prop.getWriteMethod().invoke(object, aValue);
            } catch (Exception ex) {
                Logger.getLogger(PropertyGrid.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public PropertyDescriptor getDescriptor(int row){
            return properties.get(row);
        }

        @Override
        public void addTableModelListener(TableModelListener l) {
            
        }

        @Override
        public void removeTableModelListener(TableModelListener l) {
            
        }
    }
}
