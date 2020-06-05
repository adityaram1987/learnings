package miscellaneous;

import static assisting.ProtectedVariables.*;
import static assisting.ProtectedVariables.ProtectedInnerClass.*;

public class CheckingProtected{
	
	public static final int var = getVarInnerProtected();
	
	void accessProtected() {
		System.out.println(var_public);
		System.out.println(getVarProtected());
		System.out.println(var_inner_public);
		System.out.println(getVarInnerProtected());
	}
}
