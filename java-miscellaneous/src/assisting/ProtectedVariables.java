package assisting;

public class ProtectedVariables {
	public static final int var_public = 1;
	private static final int var_protected = 2;
	
	public static class ProtectedInnerClass {
		public static final int var_inner_public = 3;
		private static final int var_inner_protected = 4;
		public static int getVarInnerProtected() {
			return var_inner_protected;
		}
	}

	public static int getVarProtected() {
		return var_protected;
	}
}
