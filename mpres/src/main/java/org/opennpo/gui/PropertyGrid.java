/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opennpo.gui;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Nate Jones
 */
public class PropertyGrid extends JPanel {
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
    }
    
    public static class BeanTableModel implements TableModel{
        private BeanInfo info;
        private Object object;
        
        public BeanTableModel(Object bean){
            try {
                object = bean;
                info = Introspector.getBeanInfo(bean.getClass());
            } catch (IntrospectionException ex) {
                Logger.getLogger(PropertyGrid.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public int getRowCount() {
            if((info==null)||(info.getPropertyDescriptors()==null)){
                return 0;
            }
            else{
                return info.getPropertyDescriptors().length;
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
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if((info==null)||(info.getPropertyDescriptors()==null)){
                return null;
            }
            else{
                PropertyDescriptor prop = info.getPropertyDescriptors()[rowIndex];
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
            }
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            
        }

        @Override
        public void addTableModelListener(TableModelListener l) {
            
        }

        @Override
        public void removeTableModelListener(TableModelListener l) {
            
        }
    }
}
