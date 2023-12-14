package br.com.helpusz.util;

public enum ONGActivityAreaENUM {
	ENVIRONMENT("Meio Ambiente"),
	EDUCATION("Educação"),
	HEALTH("Saúde"),
	ANIMAL_WELFARE("Bem-Estar Animal"),
	HUMAN_RIGHTS("Direitos Humanos"),
	COMMUNITY_DEVELOPMENT("Desenvolvimento Comunitário"),
	ARTS_AND_CULTURE("Artes e Cultura"),
	POVERTY_ALLEVIATION("Combate à Pobreza"),
	OTHER("Outro");

	private final String displayName;

	ONGActivityAreaENUM(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	@Override
	public String toString() {
		return displayName;
	}
}