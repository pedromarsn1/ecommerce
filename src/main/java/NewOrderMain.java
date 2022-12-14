import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
      var producer =  new KafkaProducer<String,String>(properties());
      var value = "123212,555555,777777";
      var record = new ProducerRecord<String,String>("nome_do_topico", value, value );
      producer.send(record, (data,ex) -> {
          if(ex != null){
              ex.printStackTrace();
              return;
          }
          System.out.println("sucesso!! enviando " + data.topic()
                  + ":::partition " + data.partition()
                  + "/offset " + data.offset());
      }).get();
    }

    private static Properties properties(){
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "porta do kafka");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
