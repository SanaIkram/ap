/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.*;     // for File
import java.util.*;   // for Scanner
//import static java.nio.file.FileVisitResult.*;

/**
 *
 * @author test1
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JavaApplication1 {
	static Map<String, String> m1 = new HashMap<String, String>();
	static Map<String, String> m2 = new HashMap<String, String>();

public NewClass linq;
	static int i = 0;
	static Scanner in = new Scanner(System.in);
 
      
	public class NewClass {
   

	private LinkedList<String> lnklst;
	
	private int size; 

	public NewClass() {
		lnklst = new LinkedList<String>();
		
		size = 0;
	}

	public  void add(String s) {
		lnklst.add(s);
		size++;
		
	}

	public  String remove() {
		String s;
		
		
			s = lnklst.remove();
			size--;
                        return s;
	}

	
}



	
		public void run() {
			String name;
			while ((name = linq.remove())!=null) {
				File file = new File(name);
				String entries[] = file.list();
				if (entries == null)
					continue;
				for (String entry : entries) {
					if (entry.compareTo(".") == 0)
						continue;
					
					String fn = name + "\\" + entry;
					
					
						m1.put(entry, fn);

						String lineContents=null;
						int counter=0;
						try{
	
							BufferedReader br=new BufferedReader(new FileReader(fn));
							while((lineContents=br.readLine())!=null){
							
								StringTokenizer st = new StringTokenizer(lineContents," ");
								while (st.hasMoreTokens()) {  
					                         m2.put(st.nextToken(), fn);


								}
								counter++;
							}
						}
						catch(FileNotFoundException fne){
							fne.printStackTrace();
						}
						catch(IOException io){
							io.printStackTrace();
						}

					


					
				}
			}
		}
	

	

	


	

	public void processDirectory(String dir) {
		try{
			File file = new File(dir);
			if (file.isDirectory()) {
				String entries[] = file.list();
			
					linq.add(dir);

				for (String entry : entries) {
					String subdir;
					if (entry.compareTo(".") == 0)
						continue;
					
					if (dir.endsWith("\\"))
						subdir = dir+entry;
					else
						subdir = dir+"\\"+entry;
					processDirectory(subdir);
				}
			}}catch(Exception e){}
	}

	public static void main(String Args[]) {

		JavaApplication1 fc = new JavaApplication1();


		System.out.println("Enter the directory name");
		String Directory_Name;
		Directory_Name = in.nextLine();
		
		System.out.println(Directory_Name);
		fc.processDirectory(Directory_Name);
                fc.run();
		System.out.println(m1);

		System.out.println(m2);
	}





}