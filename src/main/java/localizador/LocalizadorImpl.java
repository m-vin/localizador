package localizador;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.*;

import org.json.JSONObject;
import org.json.JSONException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

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
						enviarArquivos(getDirArg(), arquivo.getName());
					}
				}
			}
			if (getTypeArg().equals("txt")){
				for (File arquivo : arquivos){
					if (arquivo.isFile() && arquivo.getName().matches("[0-9]{8}\\..*[tT][xX][tT].*")){
						System.out.println(arquivo.getName());
						enviarArquivos(getDirArg(), arquivo.getName());
					}
				}
			}
			if (getTypeArg().equals("xls")){
				for (File arquivo : arquivos){
					if (arquivo.isFile() && arquivo.getName().matches(".?.*base.*.?\\..*[xX][lL][sS].*")){
						System.out.println(arquivo.getName());
						enviarArquivos(getDirArg(), arquivo.getName());
					}
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Diretório não encontrado... :(");
		} catch (IOException e){
    		e.printStackTrace();
    	}
	}

	public void enviarArquivos(String caminho, String nomeArq) throws ClientProtocolException, IOException {
		String url = "http://transporter.resultadosreais.com:8005/api/arquivos/joao-victor-de-souza-araceli";
  		URL object = new URL(url);

 		HttpURLConnection con = (HttpURLConnection) object.openConnection();
  		con.setDoOutput(true);
  		con.setDoInput(true);
  		con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
  		con.setRequestProperty("Accept", "application/json;charset=UTF-8");
  		con.setRequestMethod("POST");

  		String filePath = caminho + "/" + nomeArq;
  		try{

   		StringBody fileName = new StringBody(nomeArq);
    	JSONObject arquivo = new JSONObject();

    	arquivo.put("data[arquivo][nome]", fileName);
    	arquivo.put("data[arquivo][conteudo]", new FileBody(new File (filePath), ContentType.APPLICATION_OCTET_STREAM, filePath));

    	OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
    	wr.write(arquivo.toString());
    	wr.flush();

    	StringBuilder sb = new StringBuilder();
    	int HttpResult = con.getResponseCode();

      		if (HttpResult == HttpURLConnection.HTTP_OK){
	        	BufferedReader br = new BufferedReader(
	            	new InputStreamReader(con.getInputStream(), "utf-8"));
	        	String line = null;
	        	while ((line = br.readLine()) != null) {
	          		sb.append(line + "\n");
	        	}
	        	br.close();
	        	System.out.println("" + sb.toString());
      		} else {
        		System.out.println(con.getResponseMessage());
      		}

    	} catch (ClientProtocolException e) {
      		e.printStackTrace();
    	} catch (JSONException e){
      		e.printStackTrace();
    	} catch (IOException e){
    		e.printStackTrace();
    	}
  	}
}
