package arpad.bank.bankeventstore.controllers;

import arpad.bank.bankeventstore.enitys.Event;
import arpad.bank.bankeventstore.enitys.TypeOfEvent;
import arpad.bank.bankeventstore.eventStoreDTOs.TransferEvent;
import arpad.bank.bankeventstore.repository.EntityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Slf4j
@AllArgsConstructor
@Controller
public class TransferEventController {

	private EntityRepository entityRepository;

	@Transactional
	@RabbitListener(queues = "${bank.transfer.queue.name}")
	public void storeTransferEvent(TransferEvent transferEvent){
		log.info("Received transfer event: " + transferEvent.getTransferNumber());
		String eventAsJson = eventToJsonMapper(transferEvent);

		Event event = new Event();
		event.setTimestamp(new Timestamp(System.currentTimeMillis()));
		event.setEventAsJson(eventAsJson);
		event.setEventType(TypeOfEvent.TransferEvent);
		entityRepository.save(event);
	}

	private String eventToJsonMapper(Object event){
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String eventAsJson = null;
		try {
			eventAsJson = ow.writeValueAsString(event);
		} catch (JsonProcessingException e) {
			log.error("event could not be parsed to json", e);
		}

		return eventAsJson;
	}
}
