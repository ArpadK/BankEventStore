package arpad.bank.bankeventstore.enitys;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Timestamp timestamp;

	@Column
	private TypeOfEvent eventType;

	@Column
	private String eventAsJson;
}
