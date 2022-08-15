
public class Album {
    
    private String name;
    private String condition;
    private PhotoManager manager;
    private int nbComps;
    
    public Album(String name, String condition, PhotoManager manager){                     
        this.name = name;
        this.condition = condition;
        this.manager = manager;
    }
    public String getName(){
        return name;
    }
    public String getCondition(){
        return condition;
    }
    public PhotoManager getManager(){
        return manager;
    }
    public LinkedList<Photo> getPhotos(){
        LinkedList<Photo> allData = new LinkedList<Photo>();
        if(condition==null || manager==null)
            return allData;
        else if(condition.equals(""))
            return manager.getAllData();
        LinkedList<String> allTags = new LinkedList<String>();
        allTags = getTags(condition);
        allTags.findFirst();
        if(isExist(allTags)){
            allTags.findFirst();
            while(!allTags.last()){
                marge(manager.getPhotos().getTagData(allTags.retrieve()), allData);            
                allTags.findNext();
            }
            marge(manager.getPhotos().getTagData(allTags.retrieve()), allData);
        }else{
            allData = new LinkedList<Photo>();
        }
        isValidCondition(allTags, allData);
        return allData;
    }
    public int getNbComps(){ 
        nbComps=0;
        if(condition.equals(""))
            return 0;
        LinkedList<String> allTags = new LinkedList<String>();
         allTags = getTags(condition);
        allTags.findFirst();
        while(!allTags.last()){
            manager.getPhotos().findKey(allTags.retrieve());
            nbComps+= manager.getPhotos().getElementNb();
            allTags.findNext();
        }
        manager.getPhotos().findKey(allTags.retrieve());
        nbComps+= manager.getPhotos().getElementNb();
        return nbComps;
    }
    private void isValidCondition(LinkedList<String> tags, LinkedList<Photo> allData){
        if(allData.empty() || tags.empty())
            return;            
        allData.findFirst();
        while(!allData.last()){
            if(!validAllTags(tags, allData.retrieve().getTags())){
                allData.remove();
            }else
                allData.findNext();
        }
        if(!validAllTags(tags, allData.retrieve().getTags()))
            allData.remove();
    }
    private boolean validAllTags(LinkedList<String> conditionTags, LinkedList<String> photoTag) {
        if(photoTag.empty())
            return false;
        conditionTags.findFirst();
        while(!conditionTags.last()){
            if (!ValidTag(photoTag, conditionTags.retrieve())) {
            return false;
            }
            conditionTags.findNext();
        }
        if (!ValidTag(photoTag, conditionTags.retrieve())) {
            return false;
        }
        return true;
    }
    private boolean ValidTag(LinkedList<String> photoTags, String conditionTag){
        photoTags.findFirst();
        while (!photoTags.last()) {
            if (photoTags.retrieve().equals(conditionTag))
            return true;
            photoTags.findNext();
        }
        if (photoTags.retrieve().equals(conditionTag))
            return true;
        return false;
    }
    private boolean marge(LinkedList<Photo> BSTData, LinkedList<Photo> allData){
        if(BSTData.empty())
            return false;
        if(!allData.empty()){
            BSTData.findFirst();
            while(!BSTData.last()){
                allData.findFirst();
                while(!allData.last()){
                    if(allData.retrieve().getPath().equals(BSTData.retrieve().getPath())){
                        if(!BSTData.last()){
                            BSTData.findNext();
                            allData.findFirst();                            
                        }else{
                            break;
                        }
                    }
                    allData.findNext();
                }
                if(!allData.retrieve().getPath().equals(BSTData.retrieve().getPath())){
                    allData.insert(BSTData.retrieve());
                }
                if(BSTData.last())
                    break;
                BSTData.findNext();
            }
            allData.findFirst();
            while(!allData.last()){
                if(allData.retrieve().getPath().equals(BSTData.retrieve().getPath()))
                    break;
                allData.findNext();
            }
            if(!allData.retrieve().getPath().equals(BSTData.retrieve().getPath())){
                allData.insert(BSTData.retrieve());
            }
            return true;
        }else{
            BSTData.findFirst();
            while(!BSTData.last()){
                allData.insert(BSTData.retrieve());
                BSTData.findNext();
            }
            allData.insert(BSTData.retrieve());
            return true;
        }
    }    
    private boolean isExist(LinkedList<String> allTags){
        allTags.findFirst();
        while(!allTags.last()){
            if(!manager.getPhotos().findKey(allTags.retrieve()))
                return false;
            allTags.findNext();
        }
        if(!manager.getPhotos().findKey(allTags.retrieve()))
            return false;
        return true;
    }
    private int nbTags(String condition){
        int nbTags=0;
        for(int i=0;i<condition.length();i++){
            if(i+3<condition.length())
                if(condition.substring(i, i+3).equals("AND")){
                    i+=3;
                    nbTags++;
                }
        }
        nbTags++;
        return nbTags;
    }
    private LinkedList<String> getTags(String condition){
        LinkedList<String> allTags = new LinkedList<String> ();
        int begging=0;
        for(int i=0;i<condition.length();i++){
            if(i+5<condition.length())
                if(condition.substring(i, i+5).equals(" AND ")){
                    allTags.insert(condition.substring(begging,i));
                    i+=5;
                    begging=i;                    
                }
        }
        allTags.insert(condition.substring(begging));
        return allTags;
    }

}
