package minidraw.standard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

/**
 * ImageManager is a singleton class that acts as a centralized
 * database of all images to be used in a MiniDraw application. It
 * takes care of automatically loading images to be used in the
 * JHotDraw framework and builds a Map that maps from image names
 * (without extension) to image instances. It assumes the existence
 * of a file "preload.lst" located in a directory named "resource" in
 * the root of the classpath. This text file must contain a list of
 * filenames identifying images files to be loaded. The filenames
 * must define the GIF files without extension; one line per file.
 * <p/>
 * <p/>
 * Example: if the directory contains
 * <pre>
 * resource/board.gif
 * resource/whitestone.gif
 * resource/blackstone.gif
 * </pre>
 * then the preload.lst file must contain
 * <pre>
 * board
 * whitestone
 * blackstone
 * </pre>
 * <p/>
 * <p/>
 * This class is to be used instead of the IconKit class in the
 * standard JHD framework.
 * <p/>
 * As the ImageManager is a singleton class you access it using
 * <tt>ImageManager.getSingleton()</tt>. The MinimalApplication
 * creates the ImageManager automatically.
 * <p/>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Computer Science Department
 * Aarhus University
 * <p/>
 * This source code is provided WITHOUT ANY WARRANTY either
 * expressed or implied. You may study, use, modify, and
 * distribute it for non-commercial purposes. For any
 * commercial use, see http://www.baerbak.com/
 */

public class ImageManager implements ImageRepository {

    private static final String RESOURCE_PATH = "/resource/";

    private Map<String, Image> name2Image;

    private static ImageRepository singleton;

    public static final String PROP_NAME_FOR_SINGLETON_CLAZZ = "minidraw.ImageRepository";

    static {
        String clazzname = System.getProperty(PROP_NAME_FOR_SINGLETON_CLAZZ);

        if (clazzname != null) {
            try {
                Class c = Class.forName(clazzname);
                singleton = (ImageRepository) c.newInstance();
            } catch (ClassNotFoundException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        }
        if (singleton == null)
            singleton = new ImageManager();
    }

    private ImageManager() {
        registerAllImages();
    }

    public static ImageRepository getSingleton() {
        return singleton;
    }

    /**
     * load all gif files in resource folder into the image manager.
     */
    private void registerAllImages() {
        name2Image = new Hashtable<String, Image>();

        try {
            java.net.URL _url = getClass().getResource(RESOURCE_PATH);
            if (_url == null) {
                throw new RuntimeException("ImageManager: URL/folder '" + RESOURCE_PATH + "' does not exist.");
            }

            File dir = url2dir(_url);
            String[] gifFileArray = gifFilesInDir(dir);

            for (String s : gifFileArray) {
                // Load and register the image
                Image img = ImageIO.read(getClass().getResource(RESOURCE_PATH + s));
                if (img == null) {
                    throw new RuntimeException("ImageManager: Did not find image named " + s);
                }
                String filenameNoExtension = s.substring(0, s.length() - 4);
                name2Image.put(filenameNoExtension, img);
            }

        } catch (Exception e) {
            throw new RuntimeException("Exception caught " + e);
        }
    }

    private String[] gifFilesInDir(File dir) {
        // create a filter to find .GIF files.
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".gif") || name.endsWith(".GIF");
            }
        };
        // Retrieve array of gif file names in resource folder
        return dir.list(filter);
    }

    private File url2dir(URL _url) {
        // Convert URL to a File (from Kohsuke Kawaguchi's Blog)
        File dir;
        try {
            dir = new File(_url.toURI());
        } catch (URISyntaxException e) {
            dir = new File(_url.getPath());
        }
        return dir;
    }

    public Image getImage(String shortName) {
        Image img = name2Image.get(shortName);

        if (img == null) {
            throw new RuntimeException("ImageManager: No image named " +
                    shortName + " has been found in " +
                    RESOURCE_PATH + " folder.");
        }
        return img;
    }

}
