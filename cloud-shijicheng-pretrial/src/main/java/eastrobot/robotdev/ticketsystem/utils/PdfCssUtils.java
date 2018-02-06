package eastrobot.robotdev.ticketsystem.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;

/**
 * pdf css style utils
 * @author luotao
 *
 */
public class PdfCssUtils {
	public static final float fontHeight25 = 25;
	public static final float fontHeight20 = 20;
	public static final float fontHeight30 = 30;
	public static final float underlineHeight = 14;
	
	public static void setHeaderFontStyleAlingCenter(PdfPCell infoHeaderCell){
		infoHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        infoHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoHeaderCell.setFixedHeight(fontHeight25);
        infoHeaderCell.setColspan(2);
        infoHeaderCell.setBorder(0);
	}
	
	public static void setHeaderFontStyleAlingCenter(PdfPCell infoHeaderCell, int colspan){
		infoHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        infoHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoHeaderCell.setFixedHeight(fontHeight25);
        infoHeaderCell.setBorder(0);
        infoHeaderCell.setColspan(colspan);
	}
	
	public static void setHeaderFontStyleAlingLeft(PdfPCell infoHeaderCell, int colspan){
		infoHeaderCell.setHorizontalAlignment(Element.ALIGN_LEFT);
//        infoHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoHeaderCell.setFixedHeight(fontHeight25);
        infoHeaderCell.setBorder(0);
        infoHeaderCell.setColspan(colspan);
	}
	
	public static void setHeaderFontStyleAlingRight(PdfPCell infoHeaderCell, int colspan){
		infoHeaderCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        infoHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoHeaderCell.setFixedHeight(fontHeight25);
        infoHeaderCell.setBorder(0);
        infoHeaderCell.setColspan(colspan);
	}
	
	public static void setBodyFontStyleAlingLeft(PdfPCell infoBodyCell){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setFixedHeight(fontHeight20);
        infoBodyCell.setBorder(0);
	}
	
	public static void setBodyFontStyleAlingLeft(PdfPCell infoBodyCell, int colspan){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setFixedHeight(fontHeight20);
        infoBodyCell.setBorder(0);
        infoBodyCell.setColspan(colspan);
	}
	
	public static void setBodyFontStyleAlingLeft(PdfPCell infoBodyCell, int colspan, float fontHeight){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setFixedHeight(fontHeight);
        infoBodyCell.setBorder(0);
        infoBodyCell.setColspan(colspan);
        infoBodyCell.setPaddingTop(5);
        infoBodyCell.setPaddingBottom(5);
	}
	
	public static void setBodyFontStyleAlingLeftColspan2(PdfPCell infoBodyCell){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setFixedHeight(fontHeight20);
        infoBodyCell.setColspan(2);
        infoBodyCell.setBorder(0);
	}
	
	public static void setBodyFontStyleAlingRight(PdfPCell infoBodyCell){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setFixedHeight(fontHeight20);
        infoBodyCell.setBorder(0);
	}
	
	public static void setBodyFontStyleAlingLeftHeight25(PdfPCell infoBodyCell){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setFixedHeight(fontHeight25);
        infoBodyCell.setBorder(0);
	}
	
	public static void setBodyFontStyleAlingLeftHeight25WithColor(PdfPCell infoBodyCell){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setFixedHeight(fontHeight30);
        infoBodyCell.setBorder(0);
        infoBodyCell.setBackgroundColor(new BaseColor(136,204,234));
	}
	
	public static void setBodyFontStyleAlingLeftColspan2Height25(PdfPCell infoBodyCell){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setFixedHeight(fontHeight25);
        infoBodyCell.setColspan(2);
        infoBodyCell.setBorder(0);
	}
	
	public static void setBodyFontStyleAlingRightHeight25(PdfPCell infoBodyCell){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setFixedHeight(fontHeight25);
        infoBodyCell.setBorder(0);
	}
	
	public static void setBodyFontStyleAlingRightHeight25WithColor(PdfPCell infoBodyCell){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setFixedHeight(fontHeight30);
        infoBodyCell.setBorder(0);
        infoBodyCell.setBackgroundColor(new BaseColor(136,204,234));
	}

	public static void setBodyFontStyleAlingLeftWithBlueBackground(PdfPCell infoBodyCell){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setFixedHeight(fontHeight20);
        infoBodyCell.setBackgroundColor(new BaseColor(17,152,213));
        infoBodyCell.setColspan(2);
        infoBodyCell.setBorder(0);
	}
	
	public static void setAdjunctStyle(PdfPCell infoBodyCell){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setBorder(0);
        infoBodyCell.setPadding(20);
	}
	
	public static void setUnderLineStyle(PdfPCell infoBodyCell){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setColspan(2);
        infoBodyCell.setBorder(0);
        infoBodyCell.setCalculatedHeight(100);
        infoBodyCell.setNoWrap(true);//display whole line on the page 
	}
	
	public static void setBodyFontStyle(PdfPCell infoBodyCell, int colspan){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setColspan(colspan);
        infoBodyCell.setBorderColor(new BaseColor(221,221,221));
        infoBodyCell.setFixedHeight(fontHeight20);
//        infoBodyCell.setNoWrap(true);
	}
	
	public static void setBodyTableFontStyle(PdfPCell infoBodyCell,int colspan){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setColspan(colspan);
        infoBodyCell.setBorderColor(new BaseColor(221,221,221));
        infoBodyCell.setFixedHeight(fontHeight20);
        infoBodyCell.setNoWrap(true);
	}
	
	public static void setBodyFontStyleWithColorGray1(PdfPCell infoBodyCell){
		setBodyFontStyleWithColorGray1(infoBodyCell, 1);
	}
	
	public static void setBodyFontStyleWithColorGray2(PdfPCell infoBodyCell){
		setBodyFontStyleWithColorGray2(infoBodyCell, 1, Element.ALIGN_LEFT);
	}
	
	public static void setBodyFontStyleWithColorWrite(PdfPCell infoBodyCell){
		setBodyFontStyleWithColorWrite(infoBodyCell, 1, Element.ALIGN_LEFT);
	}
	
	public static void setBodyFontStyleWithCenterColorGray1(PdfPCell infoBodyCell){
		setBodyFontStyleWithCenterColorGray1(infoBodyCell, 1);
	}
	
	public static void setBodyFontStyleWithCenterColorGray1(PdfPCell infoBodyCell, int colspan){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setColspan(colspan);
        infoBodyCell.setBackgroundColor(new BaseColor(224,224,224));
        infoBodyCell.setBorderColor(new BaseColor(221,221,221));
        infoBodyCell.setFixedHeight(fontHeight20);
	}
	
	public static void setBodyFontStyleWithColorGray1(PdfPCell infoBodyCell, int colspan){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setColspan(colspan);
        infoBodyCell.setBackgroundColor(new BaseColor(224,224,224));
        infoBodyCell.setBorderColor(new BaseColor(221,221,221));
        infoBodyCell.setFixedHeight(fontHeight25);
	}
	
	public static void setBodyFontStyleWithColorGray2(PdfPCell infoBodyCell, int colspan, int align){
		infoBodyCell.setHorizontalAlignment(align);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setColspan(colspan);
        infoBodyCell.setBackgroundColor(new BaseColor(249,249,249));
        infoBodyCell.setBorderColor(new BaseColor(221,221,221));
        infoBodyCell.setFixedHeight(fontHeight25);
	}
	
	public static void setBodyFontStyleWithColorWrite(PdfPCell infoBodyCell, int colspan, int align){
		infoBodyCell.setHorizontalAlignment(align);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setColspan(colspan);
        infoBodyCell.setBorderColor(new BaseColor(221,221,221));
        infoBodyCell.setFixedHeight(fontHeight25);
	}
	
	public static void setBodyFontStyleWrap(PdfPCell infoBodyCell, int colspan){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setColspan(colspan);
        infoBodyCell.setBorderColor(new BaseColor(221,221,221));
	}
	
	public static void setBodyFontStyleWrap(PdfPCell infoBodyCell, int colspan,int horizontalAlignment){
		infoBodyCell.setHorizontalAlignment(horizontalAlignment);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setColspan(colspan);
        infoBodyCell.setBorderColor(new BaseColor(221,221,221));
	}
	
	
	public static void setBodyFontStyleWithNoHeightLimit(PdfPCell infoBodyCell, int colspan){
		infoBodyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        infoBodyCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        infoBodyCell.setColspan(colspan);
        infoBodyCell.setBorderColor(new BaseColor(221,221,221));
	}
	
	
	public static void setFooterFontStyle(PdfPCell infoHeaderCell, int colspan,int horizontalAlignment){
		infoHeaderCell.setHorizontalAlignment(horizontalAlignment);
        infoHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        infoHeaderCell.setFixedHeight(fontHeight25);
//        infoHeaderCell.setBorder(0);
        infoHeaderCell.setColspan(colspan);
        infoHeaderCell.setBorderColor(new BaseColor(221,221,221));
	}
}
