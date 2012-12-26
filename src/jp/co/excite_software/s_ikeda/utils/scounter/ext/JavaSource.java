package jp.co.excite_software.s_ikeda.utils.scounter.ext;

import jp.co.excite_software.s_ikeda.utils.scounter.Source;

public class JavaSource extends Source {

    @Override
    public String getFileTypeName() {
        return "Java";
    }

    @Override
    protected boolean isStatement(String line) {
        return !multiLineComment && (line.trim()).matches("^[0-9a-zA-Z@\\(\\)\\{\\},.;:=\"_+-]+.*$");
    }

    /**  */
    private boolean multiLineComment = false;
    @Override
    protected boolean isComment(String line) {

        line = line.trim();
        boolean isComment = false;

        for (int idx = 0; idx < line.length(); ) {
            if (multiLineComment) {
                isComment = true;
                if ('*' == line.charAt(idx)) {
                    idx++;
                    if (line.length() <= idx) {
                        break;
                    }

                    if('/' == line.charAt(idx)){
                        idx++;
                        multiLineComment = false;
                    }
                }
                else {
                    idx++;
                }
            }
            else {
                if ('/' == line.charAt(idx)) {
                    idx++;
                    if (line.length() <= idx) {
                        break;
                    }

                    if ('/' == line.charAt(idx)){
                        idx++;
                        isComment = true;
                    }
                    else if ('*' == line.charAt(idx)) {
                        idx++;
                        isComment = true;
                        multiLineComment = true;
                    }
                }
                else {
                    idx++;
                }
            }
        }
    
        return isComment;
    }

}
