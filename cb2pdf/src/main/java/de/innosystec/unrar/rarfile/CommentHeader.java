/*
 * Copyright (c) 2007 innoSysTec (R) GmbH, Germany. All rights reserved.
 * Original author: Edmund Wagner
 * Creation date: 23.05.2007
 *
 * Source: $HeadURL$
 * Last changed: $LastChangedDate$
 *
 * 
 * the unrar licence applies to all junrar source and binary distributions 
 * you are not allowed to use this source to re-create the RAR compression algorithm
 *
 * Here some html entities which can be used for escaping javadoc tags:
 * "&":  "&#038;" or "&amp;"
 * "<":  "&#060;" or "&lt;"
 * ">":  "&#062;" or "&gt;"
 * "@":  "&#064;" 
 */

package de.innosystec.unrar.rarfile;

import de.innosystec.unrar.io.Raw;

/**
 * DOCUMENT ME
 *
 * @author $LastChangedBy$
 * @version $LastChangedRevision$
 */
public class CommentHeader extends BaseBlock {
	
	public static final short commentHeaderSize = 6;
	
	private short unpSize;
	private byte unpVersion;
	private byte unpMethod;
	private short commCRC;
	
	
	public CommentHeader(BaseBlock bb, byte[] commentHeader){
		super(bb);
		
		int pos =0;
		unpSize = Raw.readShortLittleEndian(commentHeader, pos);
		pos += 2;
		unpVersion |= commentHeader[pos]&0xff;
		pos++;
		
		unpMethod |= commentHeader[pos]&0xff;
		pos++;
		commCRC =Raw.readShortLittleEndian(commentHeader, pos);
		
	}
	
	public short getCommCRC() {
		return commCRC;
	}
	public void setCommCRC(short commCRC) {
		this.commCRC = commCRC;
	}
	public byte getUnpMethod() {
		return unpMethod;
	}
	public void setUnpMethod(byte unpMethod) {
		this.unpMethod = unpMethod;
	}
	public short getUnpSize() {
		return unpSize;
	}
	public void setUnpSize(short unpSize) {
		this.unpSize = unpSize;
	}
	public byte getUnpVersion() {
		return unpVersion;
	}
	public void setUnpVersion(byte unpVersion) {
		this.unpVersion = unpVersion;
	}
	
	
}
