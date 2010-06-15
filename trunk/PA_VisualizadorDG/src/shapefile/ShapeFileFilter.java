package shapefile;

/**
 *
 * @author Carlos
 */

import java.io.File;

import javax.swing.filechooser.FileFilter;

//Esta classe foi baseada nos estudos feitos no site:
// http://java.sun.com/docs/books/tutorial/uiswing/components/filechooser.html
public class ShapeFileFilter extends FileFilter {
     public final static String shp = "shp";

	    public boolean accept(File f) {
	    	if (f.isDirectory()) {
	            return true;
	        }

	        String extension = getExtension(f);
	        if (extension != null) {
	            if (extension.equals(shp) )
	                    return true;
	            else
	                return false;
	        }

	        return false;
	    }

	    //The description of this filter
	    public String getDescription() {
	        return "Shape File";
	    }


    public String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

}


