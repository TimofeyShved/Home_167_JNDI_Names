package com.company;

import javax.naming.*;
import java.io.File;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws NamingException {
        // можно создавать имена пути на вроде ДНС (jndi - создание имён и свазывания их с объектами)
	    //Например у нас есть путь
        String name = "C:/Users/Admin/IdeaProjects/Home_167_JNDI_Names/text.txt";

        // прописываем настройки jndi (незабыть включить библиотеку RefFSContextFactory)
        Properties prop = new Properties();
        prop.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        prop.put(Context.PROVIDER_URL, "file:///");

        // включаем настройки
        Context initialContext = new InitialContext(prop);

        // получаем объект от имени файла
        File obj = (File) initialContext.lookup(name);
        System.out.println(obj.getName()); // вывели text.txt
        if (name.equals("")){
            System.out.println("Нету файла с таким именем");
        } else {
            System.out.println(name +" | "+ obj); // если нашли его
        }

        // можно получить
        System.out.println("---------------- Список всех объектов:");
        NamingEnumeration listName = initialContext.list("C:/Users/Admin/IdeaProjects/Home_167_JNDI_Names/");

        while (listName.hasMore()){
            NameClassPair nc = (NameClassPair) listName.next();
            System.out.println(nc);
        }

        // можно получить
        System.out.println(" ---------------- Имя и сам объект:");
        NamingEnumeration bindingName = initialContext.listBindings("C:/Users/Admin/IdeaProjects/Home_167_JNDI_Names/");

        while (bindingName.hasMore()){
            Binding bd = (Binding) bindingName.next();
            System.out.println(bd.getName()+" [Его объект ->]  "+bd.getObject());
        }
    }
}
