package cn_rt.idsbase.widget;

/**
 * Created by ${zml} on 2017/2/17.
 *
 */
public class SetArgType {
    private int messageType;
    private String jsonData;
    private int programType;
    private int subType;
    public SetArgType(int messageType, String jsonData,int programType,int subType){
        this.messageType = messageType;
        this.jsonData = jsonData;
        this.programType = programType;
        this.subType = subType;

    }


    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }


    public int getProgramType() {
        return programType;
    }

    public void setProgramType(int programType) {
        this.programType = programType;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
