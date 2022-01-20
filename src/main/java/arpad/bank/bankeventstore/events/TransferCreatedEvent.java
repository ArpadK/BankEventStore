package arpad.bank.bankeventstore.events;

import lombok.Data;

@Data
public class TransferCreatedEvent {
	private String transferNumber;
	private String rekeningNummer;
	private String TegenRekening;
	private String amount;
	private TypeOfMutatie typeOfMutatie;

}
