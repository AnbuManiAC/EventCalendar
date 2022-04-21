package database;

import java.util.ArrayList;
import java.util.List;

import exception.EventDoesNotExistsException;
import exception.InvalidInviteeException;
import exception.UserDoesNotExistsException;
import model.Event;
import model.EventInvitation;
import model.InvitationStatus;
import model.User;

public class EventInvitationQueryManager {

	EventInvitationRepository eventInvitationRepository;
	UserAuthRepository userAuthRepository;
	CalendarEventRepository eventRepository;

	public EventInvitationQueryManager() {
		eventInvitationRepository = EventInvitationRepository.getInstance();
		userAuthRepository = UserAuthRepository.getInstance();
		eventRepository = CalendarEventRepository.getInstance();
	}

	public void invite(User inviter, String inviteeEmail, int eventId)
			throws UserDoesNotExistsException, EventDoesNotExistsException, InvalidInviteeException {
		User invitee = userAuthRepository.getUserByEmail(inviteeEmail);
		if (invitee == null)
			throw new UserDoesNotExistsException();
		Event event = eventRepository.getEventById(inviter, eventId);
		if (event == null)
			throw new EventDoesNotExistsException();
		if (invitee.getEmail().equals(inviter.getEmail()))
			throw new InvalidInviteeException();
		EventInvitation eventInvitation = new EventInvitation(inviter, invitee, event);
		eventInvitationRepository.insertRecord(inviter, eventInvitation);
		eventInvitationRepository.insertRecord(invitee, eventInvitation);
	}
		
	public boolean cancleInvite(User user, int eventInviteId) {
		return eventInvitationRepository.removeRecord(user, eventInviteId);
	}

	public List<EventInvitation> getSentInvitation(User user) {
		List<EventInvitation> allInvitations = eventInvitationRepository
				.getEventInvitationForUser(user);
		List<EventInvitation> sentInvitations = new ArrayList<>();
		if (allInvitations.size() > 0)
			for (EventInvitation eventInvitation : allInvitations) {
				if (eventInvitation.getInviter().getEmail().equals(userAuthRepository.getCurrentUser().getEmail()))
					sentInvitations.add(eventInvitation);
			}
		return sentInvitations;
	}

	public List<EventInvitation> getReceivedInvitation(User user) {
		List<EventInvitation> allInvitations = eventInvitationRepository
				.getEventInvitationForUser(user);
		List<EventInvitation> receivedInvitation = new ArrayList<>();
		if (allInvitations.size() > 0)
			for (EventInvitation eventInvitation : allInvitations) {
				if (eventInvitation.getInvitee().getEmail().equals(userAuthRepository.getCurrentUser().getEmail()))
					receivedInvitation.add(eventInvitation);
			}
		return receivedInvitation;
	}

	public boolean acceptInvite(User user, int eventInvitationId) {
		return changeInvitationStatus(user, eventInvitationId, InvitationStatus.ACCEPTED);
		
	}

	public boolean rejectInvite(User user, int eventInvitationId) {
		return changeInvitationStatus(user, eventInvitationId, InvitationStatus.REJECTED);
	}

	public void changeInvitationStatusAfterEventDeletion(User user, int eventId) {
		List<EventInvitation> eventInvitationsForEventId = eventInvitationRepository.getEventInvitationByEventId(user, eventId);
		for(EventInvitation eventInvitation : eventInvitationsForEventId) {
			changeInvitationStatus(user, eventInvitation.getId(), InvitationStatus.EXPIRED);
		}
	}
	
	private boolean changeInvitationStatus(User user, int eventInvitationId, InvitationStatus status) {
		EventInvitation eventInvitation = eventInvitationRepository.getEventInvitationById(user, eventInvitationId);
		if (eventInvitation != null) {
			return eventInvitation.setStatus(status);
		}
		return false;
	}
}
