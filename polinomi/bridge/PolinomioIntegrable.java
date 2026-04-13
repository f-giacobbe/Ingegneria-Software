package is.polinomi.bridge;

import is.polinomi.Monomio;

public class PolinomioIntegrable extends PolinomioConcreto {

	public PolinomioIntegrable integrate(){
		PolinomioIntegrable ris= new PolinomioIntegrable();
		for(Monomio m:this){
			double c= m.coeff();
			int g=m.grado();
			
			Monomio mint= new Monomio(c/(g+1),g+1);
			ris.add(mint);
		}
		return ris;
	}
	
	
}
