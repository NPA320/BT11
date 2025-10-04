package BT11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginReponse {
	private String token;
	private long expiresIn;
	
	public String gettoken()
	{
		return token;
		
	}

}
