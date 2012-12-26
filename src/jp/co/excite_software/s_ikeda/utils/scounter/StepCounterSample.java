package jp.co.excite_software.s_ikeda.utils.scounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import jp.co.excite_software.s_ikeda.utils.scounter.ext.JavaSource;

/**
 * author s-ikeda@excite-software.co.jp
 */
public class StepCounterSample {

    private static final String INDENT = "    ";

    public static void main(String[] args) {
        File file;
        if(args.length == 0) {
            file = new File("./src/jp/co/excite_software/s_ikeda/utils/scounter/StepCounterSample.java");
        }
        else {
            file = new File(args[0]);
        }
        System.out.println("-");
        System.out.println("target:");
        System.out.println(INDENT + file);

        try {
            // �R�����g
            Source source = JavaSource.count(file, JavaSource.class);
            System.out.println("result:");
            System.out.println(INDENT + source); //  �s���R�����g�@������� Statement �Ƃ��Ĉ���
        }
        catch(FileNotFoundException e){
            System.err.println("error:");
            System.err.println(INDENT + "�w�肳�ꂽ�t�@�C����������܂���ł����B");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (InstantiationException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
        }
    }

    /* static {
        
    }*/

}
