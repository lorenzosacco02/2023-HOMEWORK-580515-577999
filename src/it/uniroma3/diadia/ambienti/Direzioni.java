package it.uniroma3.diadia.ambienti;

public enum Direzioni {
	NORD(){
		@Override
		public String toString() {
			return "nord";
		}
		public Direzioni Opposta() {
			return SUD;
		}
	},
	SUD(){
		@Override
		public String toString() {
			return "sud";
		}

		public Direzioni Opposta() {
			return NORD;
		}

	},
	OVEST(){
		@Override
		public String toString() {
			return "ovest";
		}

		public Direzioni Opposta() {
			return EST;
		}
	},
	EST(){
		@Override
		public String toString() {
			return "est";
		}
		public Direzioni Opposta() {
			return OVEST;
		}
	};

	public static Direzioni getOpposta(Direzioni a) {
		switch (a.ordinal()) {
		case 0: {
			return SUD;
		}
		case 1: {
			return NORD;
		}
		case 2: {
			return EST;
		}
		case 3: {
			return OVEST;
		}
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		return "nord, sud, ovest, est";
	}
}
