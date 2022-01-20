package arpad.bank.bankeventstore.enitys;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	@Column
	private Timestamp timestamp;

	@Getter
	@Setter
	@Column
	private TypeOfEvent eventType;

	@Getter
	@Setter
	@Column
	private String eventAsJson;
}
