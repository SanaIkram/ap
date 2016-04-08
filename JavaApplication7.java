/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

import java.io.*;     // for File
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// for Scanner
//import static java.nio.file.FileVisitResult.*;

/**
 *
 * @author test1
 */


public class JavaApplication7 {
	static Map<String, LinkedList<flData>> m2 = new HashMap<String, LinkedList<flData>>();
        //map for file size
        static Map<Long, LinkedList<flData>> Fsize = new HashMap<Long, LinkedList<flData>>();
        //map for modified date
         static Map<java.util.Date, LinkedList<flData>> Fmod = new HashMap<java.util.Date, LinkedList<flData>>();
	static java.util.Date currDate = Calendar.getInstance().getTime();
       public  crawler c = new crawler();

   
    public   crawler c2 = new crawler();


	static int i = 0;
	static Scanner in = new Scanner(System.in);
 
      //New class saves a linklist containing all directories
	public static class NewClass {
   
     
	public LinkedList<String> lnklst;
	
	public int size; 

	public  NewClass() {
		lnklst = new LinkedList<String>();
		
		size = 0;
	}

	public  void addd(String s) {
           
		lnklst.add(s);
		size++;
               
		
	}

	public synchronized String remove() {
		String s;
		
		       if(size==0)
                           return null;
			s = lnklst.remove();
			size--;
                        return s;
	}

	
}

        public static NewClass linq = new NewClass();
        //flData contains the data of one file
   public class flData{
           
   String nm;
   java.util.Date modDate;
   flData(){
   nm="";
   modDate = null;
   
   }
   
   
   
   }
        
        
     
public  class crawler implements Runnable{

	
    crawler()
    {}
    
		public void run() {
			String name;
                       
                       
                        //while there are directories left unexplored
			while ((name = linq.remove())!=null) {
                             flData fd= new flData();
				File file = new File(name);
                                Date d= new Date(file.lastModified());
                                if (d.after(currDate)==true)
                                    continue;
				String entries[] = file.list();
                              
                                
				if (entries == null)
					continue;
				for (String entry : entries) {
                                   
					if (entry.compareTo(".") == 0)
						continue;
					
					String fn = name + "\\" + entry;
                                          fd.nm= fn;
                                          fd.modDate = d;
                                           Long lng = file.length();
					String s = "";
					
                                        LinkedList<flData> o = new LinkedList<flData>();
                                        LinkedList<flData> mn = new LinkedList<flData>();
						
						 if(Fmod.get(d)!=null)
                                                                  {
                                                                   o  =Fmod.get(d);
                                                                  
                                                                  }
                                                                    
                                                                  else{
                                                                  o.add(fd);
                                                                  
                                                                  }
					                         Fmod.put(d,o );

  
	                                        if(Fsize.get(lng)!=null)
                                                                  {
                                                                   mn  =Fsize.get(lng);
                                                                  
                                                                  }
                                                                    
                                                                  else{
                                                                  mn.add(fd);
                                                                  
                                                                  }
					                         Fsize.put(lng,mn );
							
							
								StringTokenizer st = new StringTokenizer(entry," ");
								while (st.hasMoreTokens()) {  
                                                                   LinkedList<flData> l = new LinkedList<flData>();
                                                                    s = st.nextToken();
                                                                 

                                                                  if(m2.get(s)!=null)
                                                                  {
                                                                   l  =m2.get(s);
                                                                  l.add(fd);
                                                                  }
                                                                    
                                                                  else{
                                                                  l.add(fd);
                                                                  
                                                                  }
					                         m2.put(s,l );
                                                               
  
								}
								
						
						
						
					


					
				}
			}
		}
	

	

}
//search by name
public static void search(String s)
{
    LinkedList<flData> ll = new LinkedList<flData>();
    
    Pattern r = Pattern.compile(s);
    
     Iterator it = m2.entrySet().iterator();
    while (it.hasNext()) {
        Map.Entry<String, LinkedList<flData>> pair = (Map.Entry)it.next();
        
        Matcher m = r.matcher( (pair.getKey()).toString());
                pair.getValue();
                 if(m.lookingAt( )==true)
                 {
                     ll=pair.getValue();
                    
                    System.out.println(pair.getKey());
                     for(int h=0;h<ll.size();h++)
                     {
                         
                      System.out.println(ll.get(h).nm);
                     
                     }
                     
                 }
        it.remove(); // avoids a ConcurrentModificationException
    }
   
    
    
 

}



public static void searchByDate(Date s)
{
    LinkedList<flData> ll = new LinkedList<flData>();
    
    ll = Fmod.get(s);
    for(int h=0;h<ll.size();h++)
                     {
                         
                      System.out.println(ll.get(h).nm);
                     
                     }
 

}


public static void searchBySize(String s)
{
    LinkedList<flData> ll = new LinkedList<flData>();
    System.out.println("here");
  Long l= Long.valueOf( s).longValue();
   System.out.println(l);
    ll = Fsize.get(l);
    if(ll!=null)
    {
   for(int h=0;h<ll.size();h++)
                     {
                         
                      System.out.println(ll.get(h).nm);
                     
                     }
 
    }
}


	

	public void processDirectory(String dir) {
		try{
                    
			File file = new File(dir);
                         Date d= new Date(file.lastModified());
                                if (d.after(currDate)==true)
                                {
                                     System.out.println(currDate);
                                      System.out.println(d);
                                   return;
                                }
			if (file.isDirectory()) {
				String entries[] = file.list();
			       
                                 System.out.println(entries.length);
                                 System.out.println(dir);
					linq.addd(dir);
                                      //System.out.println(linq.size);
				for (int i = 0; i<entries.length; i++) {
					String subdir= entries[i];
                                         System.out.println(subdir);
					if (entries[i].compareTo(".") == 0)
						continue;
					
					if (dir.endsWith("\\"))
						subdir = dir+entries[i];
					else
						subdir = dir+"\\"+entries[i];
					processDirectory(subdir);
				}
			}
               
                }catch(Exception e){}
	}

	public static void main(String Args[]) throws InterruptedException, ParseException {

		JavaApplication7 fc = new JavaApplication7();
               
		System.out.println("Enter the directory name");
		String Directory_Name;
		Directory_Name = in.nextLine();
		
		System.out.println(Directory_Name);
                NewClass s= new NewClass();
                
		fc.processDirectory(Directory_Name);
                Thread t = new Thread(fc.c);
                 Thread t1 = new Thread(fc.c2);
                t1.start();
                
                t.start();
                  
                t1.join();
                
                t.join();
               
                
                
		System.out.println("Enter the file to be searched");
		String jj;
		jj = in.nextLine();
                search(jj);
                
                System.out.println("Enter the size of the file to be searched");
		
		jj = in.nextLine();
                searchBySize(jj);
		System.out.println("Enter the date of file to be searched by date modified");
		
		jj = in.nextLine();
                DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
Date date = format.parse(jj);
                searchByDate(date);
	}


   
        


}

