package com.bountive.util;

public class OSUtil {

	public static OSUtil.EnumOS getOSType() {
        String s = System.getProperty("os.name").toLowerCase();
        
        return s.contains("win") ? OSUtil.EnumOS.WINDOWS : 
        	(s.contains("mac") ? OSUtil.EnumOS.OSX : 
        		(s.contains("linux") ? OSUtil.EnumOS.LINUX : 
					(s.contains("unix") ? OSUtil.EnumOS.LINUX :
		        		(s.contains("solaris") ? OSUtil.EnumOS.SOLARIS : 
		        			(s.contains("sunos") ? OSUtil.EnumOS.SOLARIS : 
        						OSUtil.EnumOS.UNKNOWN)))));
    }

    public static enum EnumOS {
    	WINDOWS,
    	OSX,
    	LINUX,
        SOLARIS,
        UNKNOWN;
    }
}
