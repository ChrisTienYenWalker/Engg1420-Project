package ProjectPackage;

import java.util.ArrayList;

abstract class Filter {
    ArrayList<String> entries = new ArrayList<String>();

    public abstract void inputs(ArrayList<String> information);

    public abstract void outputs();

}
