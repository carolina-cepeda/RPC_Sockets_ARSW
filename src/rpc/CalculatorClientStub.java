package rpc;
import java.io.*;
import java.net.Socket;
import java.util.UUID;
public class CalculatorClientStub implements CalculatorService {

   private final String host;
   private final int port;

   public CalculatorClientStub(String host, int port) {
      this.host = host;
      this.port = port;
   }

   @Override
   public int add(int a, int b) {
      String id = UUID.randomUUID().toString();
      String request = "id=" + id + ";method=add;params=" + a + "," + b;
      String response = send(request);
      return parseResultOrThrow(id, response);
   }
   
   @Override
   public int subtract(int a, int b) {
      String id = UUID.randomUUID().toString();
      String request = "id=" + id + ";method=subtract;params=" + a + "," + b;
      String response = send(request);
      return parseResultOrThrow(id, response);
   }

   @Override
   public int multiply(int a, int b) {
      String id = UUID.randomUUID().toString();
      String request = "id=" + id + ";method=multiply;params=" + a + "," + b;
      String response = send(request);
      return parseResultOrThrow(id, response);
   }

   @Override
   public int divide(int a, int b) {
      String id = UUID.randomUUID().toString();
      String request = "id=" + id + ";method=divide;params=" + a + "," + b;
      String response = send(request);
      return parseResultOrThrow(id, response);
   }

   private String send(String request) {
      try (Socket socket = new Socket(host, port);
      BufferedWriter out = new BufferedWriter(new 
      OutputStreamWriter(socket.getOutputStream()));
      BufferedReader in = new BufferedReader(new 
      InputStreamReader(socket.getInputStream()))) {
         out.write(request);
         out.newLine();
         out.flush();
         return in.readLine();

      } catch (IOException e) {
         throw new RuntimeException("RPC connection failed: " + e.getMessage(), e);
         }
   }

   private int parseResultOrThrow(String id, String responseLine) {
      System.out.println("Received response: " + responseLine);
      if (responseLine == null) throw new RuntimeException("Empty response");
      var resp = RpcProtocol.parseLine(responseLine);
      if (!id.equals(resp.get("id"))) throw new RuntimeException("Mismatched response id");
      boolean ok = "true".equalsIgnoreCase(resp.get("ok"));
      if (!ok) throw new RuntimeException("RPC error: " + resp.getOrDefault("error", "unknown"));
      return Integer.parseInt(resp.get("result"));
   }
}