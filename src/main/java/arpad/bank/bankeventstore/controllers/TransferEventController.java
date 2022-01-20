package arpad.bank.bankeventstore.controllers;

import arpad.bank.bankeventstore.enitys.Event;
import arpad.bank.bankeventstore.enitys.TypeOfEvent;
import arpad.bank.bankeventstore.events.TransferCanceledEvent;
import arpad.bank.bankeventstore.events.TransferCompletedEvent;
import arpad.bank.bankeventstore.events.TransferCreatedEvent;
import arpad.bank.bankeventstore.repository.EntityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;

@Controller
@Slf4j
public class TransferEventController {

	private EntityRepository entityRepository;

	@PostMapping("/registerNewTransferEvent")
	public void registerNewTransferEvent(TransferCreatedEvent transferCreatedEvent){
		String eventAsJson = eventToJsonMapper(transferCreatedEvent);

		Event event = new Event();
		event.setTimestamp(new Timestamp(System.currentTimeMillis()));
		event.setEventAsJson(eventAsJson);
		event.setEventType(TypeOfEvent.TransferCreatedEvent);

		entityRepository.save(event);
	}

	@PostMapping("/registerNewTransferCompletedEvent")
	public void registerNewTransferCompletedEvent(TransferCompletedEvent transferCompletedEvent){
		String eventAsJson = eventToJsonMapper(transferCompletedEvent);

		Event event = new Event();
		event.setTimestamp(new Timestamp(System.currentTimeMillis()));
		event.setEventAsJson(eventAsJson);
		event.setEventType(TypeOfEvent.TransferCompleted);

		entityRepository.save(event);
	}

	@PostMapping("/registerNewTransferCanceledEvent")
	public void registerNewTransferCanceledEvent(TransferCanceledEvent transferCanceledEvent){
		String eventAsJson = eventToJsonMapper(transferCanceledEvent);

		Event event = new Event();
		event.setTimestamp(new Timestamp(System.currentTimeMillis()));
		event.setEventAsJson(eventAsJson);
		event.setEventType(TypeOfEvent.TransferCanceled);

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
