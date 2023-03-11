package ProjectPackage;

import java.util.ArrayList;

class LengthFilter extends Filter {
    public LengthFilter(ArrayList<String> inputValue, ArrayList<Filter> pastEntries) {
        //if there's no entries use past entries
        getEntriesLocal("Project\\src\\ProjectPackage");
        for(String name:entries){
            System.out.println(name);
        }

    }
    public void opertaions(){

    };
    public void outputs() {
    };
}
