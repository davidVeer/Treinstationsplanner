package guiapplication.simulator.npc.traveler.states;

import guiapplication.simulator.npc.traveler.Traveler;
import guiapplication.simulator.pathfinding.PathFinding;

import java.time.LocalTime;

public class BoardingState implements TravelerState {

    private Traveler traveler;

    public BoardingState(Traveler traveler) {
        this.traveler = traveler;
        traveler.setTarget(PathFinding.getRandomTrainTarget(traveler.getJourney().getPlatform().getPlatformNumber()));
    }

    @Override
    public void handle(LocalTime time) {
        if (time.isAfter(traveler.getJourney().getDepartureTime())
                || time.equals(traveler.getJourney().getDepartureTime())) {
            traveler.setState(new LeavingState(traveler));
        }

        if (traveler.atTargetNode()) {
            traveler.setState(new FinishedState());
        }
    }

    @Override
    public String toString() {
        return "Betreed trein";
    }
}
