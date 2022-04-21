package model;

import database.UserQueryManager;
import exception.NoUserLoggedIn;

public class EventInvitation {
	private static int nextId = 1;
	private int id;
	private User inviter;
	private User invitee;
	Event eventInvitedFor;
	InvitationStatus status;

	public EventInvitation(User inviter, User invitee, Event eventInvitedFor) {
		this.id = nextId++;
		this.inviter = inviter;
		this.invitee = invitee;
		this.eventInvitedFor = eventInvitedFor;
		this.status = InvitationStatus.PENDING;
	}

	public int getId() {
		return id;
	}

	public User getInviter() {
		return inviter;
	}

	public User getInvitee() {
		return invitee;
	}

	public Event getEventInvitedFor() {
		return eventInvitedFor;
	}

	public InvitationStatus getStatus() {
		return status;
	}

	public boolean setStatus(InvitationStatus status) {
		UserQueryManager userQueryManager = new UserQueryManager();
		try {
			User user = userQueryManager.getLoggedInUser();
			if(user.getEmail().equals(this.invitee.getEmail()) && !this.status.equals(InvitationStatus.EXPIRED) && (status.equals(InvitationStatus.ACCEPTED) || status.equals(InvitationStatus.REJECTED)))
			{	
				this.status = status;
				return true;
			}
			if(user.getEmail().equals(this.inviter.getEmail()) && status.equals(InvitationStatus.EXPIRED))
			{
				this.status = status;
				return true;
			}
		} catch (NoUserLoggedIn e) {
			return false;
		}
		return false;
	}
}
