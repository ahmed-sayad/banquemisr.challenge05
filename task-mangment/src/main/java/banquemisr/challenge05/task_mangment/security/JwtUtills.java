package banquemisr.challenge05.task_mangment.security;

//import java.security.Key;
import java.util.Date;
import java.security.Key;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtills {
//    private static final Logger logger =LoggerFactory.getLogger(JwtUtills.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwtExpiration}")
    private int jwtExpiration;

public String generateJwtToken(Authentication authentication){
    UserDetails userPricipal = (UserDetails) authentication.getPrincipal();
    return Jwts.builder()
        .setSubject(userPricipal.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime()+jwtExpiration))
        .signWith(SignatureAlgorithm.HS256, key())
        .compact();
} 
		public Key key(){
			try {
			Key k =  Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));
			return k;
			}
			catch (Exception e) {
				System.out.println(e);
			}
			return null;
//		    return Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret));
			}
	public  boolean validateJwtToken(String authtoken){
	    try {
	        Jwts.parser().setSigningKey(key()).parse(authtoken);
	        return true;
	        
	    } catch (MalformedJwtException e) {
	//        logger.error("Invalid JWT Token : {}",e.getMessage());
	    }catch(ExpiredJwtException e){
	//        logger.error("JWT Token is Expired : {}",e. getMessage());
	    }catch(UnsupportedJwtException e){
	//        logger.error("Unsupported JWT :{}", e.getMessage());
	    }catch(IllegalArgumentException e){
	//        logger.error("JWT Payload is Empty: {}", e.getMessage());
	    }
	    return false;
	
	
	}

	public String getUsernameFromJwtToken(String authtoken){
	    return Jwts.parser().setSigningKey(key()).parseClaimsJws(authtoken).getBody().getSubject();
	}
}

