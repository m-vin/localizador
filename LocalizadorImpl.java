import java.io.File;

public class LocalizadorImpl implements Localizador {

	private String typeArg;
	private String dirArg;


	public LocalizadorImpl(String[] args) {
		System.out.println("-------------------------");
		System.out.println("\nLocalizador de arquivos");
		System.out.printf("Programado por m-vin\n\n");
		System.out.println("-------------------------");

		try{
			setTypeArg(args[0]);
			setDirArg(args[1]);
			procurarArquivos();
		} catch (NullPointerException e){
				System.out.println("USAGE: java TesteLocalizador <tipo-de-arquivo> <diretorio>");
		} catch (ArrayIndexOutOfBoundsException e){
				System.out.println("USAGE: java TesteLocalizador <tipo-de-arquivo> <diretorio>");
		}
	}

	public String getTypeArg(){
		return typeArg;
	}

	public void setTypeArg(String typeArg){
		this.typeArg = typeArg.toLowerCase();
	}

	public String getDirArg(){
		return dirArg;
	}

	public void setDirArg(String dirArg){
		this.dirArg = dirArg.toLowerCase();
	}

	public void procurarArquivos(){

		File diretorio = new File(getDirArg());
		File[] arquivos = diretorio.listFiles();


		/* 1. .JPG - arquivo de imagem contendo
				a palavra "foto" no nome;

		   2. .txt - arquivo de texto contendo
				uma sequencia de 8 numeros no nome;

		   3. .xls - arquivo excel contendo
				a palavra "base" no nome;		*/
		System.out.printf("Arquivos .%s encontrados no diretório %s: \n", getTypeArg(), getDirArg());

		try{
			if (getTypeArg().equals("jpg")){
				for (File arquivo : arquivos){
					if (arquivo.isFile() && arquivo.getName().matches(".?.*foto.*.?\\..*[jJ][pP][gG].*")){
						System.out.println(arquivo.getName());
					}
				}
			}
			if (getTypeArg().equals("txt")){
				for (File arquivo : arquivos){
					if (arquivo.isFile() && arquivo.getName().matches("[0-9]{8}\\..*[tT][xX][tT].*")){
						System.out.println(arquivo.getName());
					}
				}
			}
			if (getTypeArg().equals("xls")){
				for (File arquivo : arquivos){
					if (arquivo.isFile() && arquivo.getName().matches(".?.*base.*.?\\..*[xX][lL][sS].*")){
						System.out.println(arquivo.getName());
					}
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Diretório não encontrado... :(");
		}
	}
}
