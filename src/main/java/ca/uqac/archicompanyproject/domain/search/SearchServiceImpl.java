package ca.uqac.archicompanyproject.domain.search;

import ca.uqac.archicompanyproject.domain.caregiver.Caregiver;
import ca.uqac.archicompanyproject.domain.caregiver.CaregiverService;
import ca.uqac.archicompanyproject.domain.patient.Patient;
import ca.uqac.archicompanyproject.domain.room.Room;
import ca.uqac.archicompanyproject.domain.room.RoomService;
import ca.uqac.archicompanyproject.domain.users.User;
import ca.uqac.archicompanyproject.domain.users.UserService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    UserService userService;
    CaregiverService caregiverService;
    RoomService roomService;

    public List<SearchResult> getEntitiesByName(String name, String token){

        List<SearchResult> results = new ArrayList<SearchResult>();
        //Cas caregiver : acces a ses patients, pas aux autres medecins ni secretaire ni room ??
        List<User> users =  userService.getUsersWithNameContaining(name);
        List<Room> rooms;
        try {
            Caregiver caregiver = caregiverService.getCaregiverFromToken(token);
            users = users.stream().filter(u -> u instanceof Patient && ((Patient) u).getPrimaryDoctor() != null && ((Patient) u).getPrimaryDoctor().getID().equals(caregiver.getID()))
                    .collect(Collectors.toList());

        } catch (NotFoundException e) {
            //Cas secretaire, acces a tous les users, toutes les rooms
            rooms = roomService.findByNameContaining(name);

            for (Room room : rooms){
                results.add(new SearchResult(room.getClass().getSimpleName(), room.getName(), room.getRoot(), null));
            }

        }


        for (User user : users){
            results.add(new SearchResult(user.getClass().getSimpleName(), user.getFirstName() + " " + user.getLastName(), user.getRoot(), null));
        }


        return results;
    }
}
