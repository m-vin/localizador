package localizador;

import java.io.IOException;
import org.apache.http.client.ClientProtocolException;

public interface Localizador {

	public String getTypeArg();

	public void setTypeArg(String typeArg);

	public String getDirArg();

	public void setDirArg(String dirArg);

	public void procurarArquivos();

	public void enviarArquivos(String caminho, String nomeArq) throws ClientProtocolException, IOException;

}
