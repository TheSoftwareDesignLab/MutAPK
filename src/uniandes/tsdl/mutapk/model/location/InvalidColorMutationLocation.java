package uniandes.tsdl.mutapk.model.location;

import uniandes.tsdl.mutapk.model.MutationType;

public class InvalidColorMutationLocation extends MutationLocation{

	private String colorName;
	private String originalColor;
	
	
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getOriginalColor() {
		return originalColor;
	}
	public void setOriginalColor(String originalColor) {
		this.originalColor = originalColor;
    }
    
    public static MutationLocation buildLocation( String path, int startLine, int endLine, int startCol, int endCol, int lineNum, int length, MutationType mType, String colorName, String originalColor) {
		InvalidColorMutationLocation location = new InvalidColorMutationLocation();
		location.setFilePath(path);
		location.setStartLine(startLine);
		location.setEndLine(endLine);
		location.setStartColumn(startCol);
		location.setEndColumn(endCol);
		location.setType(mType);
		location.setLine(lineNum);
        location.setLength(length);
        location.setColorName(colorName);
        location.setOriginalColor(originalColor);
		return location;
	}

}