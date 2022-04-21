package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.EventInvitation;
import model.User;

public class EventInvitationRepository {

	private final Map<User, List<EventInvitation>> userEventInvitationsMap = new HashMap<>();

	private EventInvitationRepository() {
	}

	private static EventInvitationRepository instance = null;

	static EventInvitationRepository getInstance() {
		if (instance == null)
			instance = new EventInvitationRepository();
		return instance;
	}

	void insertRecord(User user, EventInvitation invitation) {
		if (userEventInvitationsMap.containsKey(user))
			userEventInvitationsMap.get(user).add(invitation);
		else {
			List<EventInvitation> eventInvitations = new ArrayList<>();
			eventInvitations.add(invitation);
			userEventInvitationsMap.put(user, eventInvitations);
		}
	}

	boolean removeRecord(User user, int EventInvitationId) {
		EventInvitation eventInvitation = getInvitationById(user, EventInvitationId);
		if(eventInvitation!=null) {
			userEventInvitationsMap.get(user).remove(eventInvitation);
			return true;
		}
		return false;
	}
	
	EventInvitation getInvitationById(User user, int id) {
		if(userEventInvitationsMap.containsKey(user)) {
			for(EventInvitation eventInvitation : userEventInvitationsMap.get(user)) {
				if(eventInvitation.getId() == id) 
					return eventInvitation;
			}
		}
		return null;
	}
	
	List<EventInvitation> getEventInvitationByEventId(User user, int eventId) {
		List<EventInvitation> eventInvitations = getEventInvitationForUser(user);
		List<EventInvitation> eventInvitationsForEventId = new ArrayList<>();
		for(EventInvitation eventInvitation : eventInvitations) {
			if(eventInvitation.getEventInvitedFor().getId() == eventId)
				eventInvitationsForEventId.add(eventInvitation);
		}
		return eventInvitationsForEventId;
	}
	
	List<EventInvitation> getEventInvitationForUser(User user) {
		List<EventInvitation> eventInvitations = new ArrayList<>();
		if (userEventInvitationsMap.get(user) != null)
			eventInvitations.addAll(userEventInvitationsMap.get(user));
		return eventInvitations;
	}

	EventInvitation getEventInvitationById(User user, int id) {
		if (userEventInvitationsMap.containsKey(user)) {
			for(EventInvitation eventInvitation : userEventInvitationsMap.get(user)) {
				if(eventInvitation.getId() == id)
					return eventInvitation;
			}
		}
		return null;
	}
}
