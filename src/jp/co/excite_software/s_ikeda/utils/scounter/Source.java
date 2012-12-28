package jp.co.excite_software.s_ikeda.utils.scounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

abstract public class Source {

    /** ファイル名 */
    private  String  fileName;
    /** フルパス名 */
    private String pathName;
    /** 実行数  */
    private int statement;
    /** コメント行数 */
    private int comment;
    /** 空行数 */
    private int blank;
    /** 不明行数 */
    private int unknown;
    
    abstract public String getFileTypeName();
    
    abstract protected boolean isStatement(String line);
    
    abstract protected boolean isComment(String line);
    
    protected boolean isBlank(String line){
        return (line.trim()).equals("");
    }

    protected void count(String line) {

        if (line == null || isBlank(line)) {
            this.blank++;
        }
        else if (isStatement(line)) {
            this.statement++;
        }
        else if (isComment(line)) {
            this.comment++;
        }
        else {
            this.unknown++;
        }
    }

    public static Source count(File file, Class<?> clazz)
        throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException {

        Source source = (Source) clazz.newInstance();

        source.fileName = file.getName();
        source.pathName = file.getCanonicalPath();
        source.statement = 0;
        source.comment = 0;
        source.blank = 0;
        source.unknown = 0;

        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String line = null;
            while((line = br.readLine()) != null){

                source.count(line);
            }
        }
        finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            }
            catch (IOException e){
                // through
            }
        }
        return source;
    }

    public String getPathName() {
        return this.pathName;
    }

    public int getStateeentCount(){
        return this.statement;
    }

    public int getCoeeentCount(){
        return this.comment;
    }

    public int getSlankCount(){
        return this.blank;
    }

    public int getUnknownCount(){
        return this.unknown;
    }

    public int getTotalCount(){
        return this.statement + this.comment + this.blank + this.unknown;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append(this.fileName + "{");
        builder.append(this.getFileTypeName());
        builder.append(": statement=" + this.statement);
        builder.append(", comment=" + this.comment);
        builder.append(", blank=" + this.blank);
        builder.append(", unknown=" + this.unknown);
        builder.append(", total=" + this.getTotalCount() + "}");
        return builder.toString();
    }
}
