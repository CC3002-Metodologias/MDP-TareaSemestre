package aventurasMarcoyLuis.controller;

import aventurasMarcoyLuis.controller.phasesAndExceptions.*;
import aventurasMarcoyLuis.interfaces.Ienemy;
import aventurasMarcoyLuis.model.AttackType;
import aventurasMarcoyLuis.model.personajes.Boo;
import aventurasMarcoyLuis.model.personajes.Goomba;
import aventurasMarcoyLuis.model.personajes.Luis;
import aventurasMarcoyLuis.model.personajes.Marco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintStream;

public class PhasesAndExceptionsTest {
    private final GameController gameController = new GameController();
    private final PrintStream out = new PrintStream(new NullOutputStream());
    PlayerIn pLuis;
    PlayerIn pMarco;
    Marco marco;
    Luis luis;
    Phase fase;
    Boo boo;

    @BeforeEach
    public void setTests(){
        marco = new Marco();
        luis = new Luis();
        pLuis = new PlayerIn(luis);
        pMarco = new PlayerIn(marco);
        gameController.setOut(out);
        boo = new Boo();
    }

    @Test
    public void phaseNotValidOptionTest() {

        //En este momento la phase de entrada que tiene gameController es StartRoundPhase
        fase = gameController.getPhase();
        assert fase.getClass() == StartRoundPhase.class;
        try {
            fase.chooseAction(pLuis);
            // Dado la fase en que esta el contralador, este metodo debe arrojar un InvalidOptionException
            assert false;
        } catch (InvalidTransitionException | IOException | InvalidInputException e) {
            assert false;
        } catch (InvalidOptionException e) {
            assert true;
        }

        try{
            fase.endTurn();
            // Dado la fase en que esta el contralador, este metodo debe arrojar un InvalidOptionException
            assert false;
        } catch (InvalidTransitionException e) {
            assert false;
        } catch (InvalidOptionException e) {
            assert true;
        }

        try{
            fase.chooseVictimAttack(pLuis, AttackType.SALTO);
            // Dado la fase en que esta el contralador, este metodo debe arrojar un InvalidOptionException
            assert false;
        } catch (InvalidTransitionException | InvalidInputException | IOException | InvalidAttackException e) {
            assert false;
        } catch (InvalidOptionException e) {
            assert true;
        }

        try{
            fase.gameOver();
            // Dado la fase en que esta el contralador, este metodo debe arrojar un InvalidOptionException
            assert false;
        } catch (InvalidOptionException e) {
            assert true;
        }

        try{
            fase.roundOver();
            // Dado la fase en que esta el contralador, este metodo debe arrojar un InvalidOptionException
            assert false;
        } catch (InvalidTransitionException e) {
            assert false;
        } catch (InvalidOptionException e) {
            assert true;
        }

        gameController.tryToStartNewRound();
        //Ahora la fase deberia ser ChoosingActionPhase
        fase = gameController.getPhase();
        assert fase.getClass() == ChoosingActionPhase.class;
        try{
            fase.startRound();
            // Dado la fase en que esta el contralador, este metodo debe arrojar un InvalidOptionException
            assert false;
        } catch (InvalidTransitionException e) {
            assert false;
        } catch (InvalidOptionException e) {
            assert true;
        }

        try{
            fase.chooseVictimAttack(pLuis, AttackType.SALTO);
            // Dado la fase en que esta el contralador, este metodo debe arrojar un InvalidOptionException
            assert false;
        } catch (InvalidTransitionException | InvalidInputException | IOException | InvalidAttackException e) {
            assert false;
        } catch (InvalidOptionException e) {
            assert true;
        }

        try{
            fase.chooseAttack(pLuis);
            // Dado la fase en que esta el contralador, este metodo debe arrojar un InvalidOptionException
            assert false;
        } catch (InvalidInputException | IOException e) {
            assert false;
        } catch (InvalidOptionException e) {
            assert true;
        }

        try{
            fase.selectItem(pLuis);
            // Dado la fase en que esta el contralador, este metodo debe arrojar un InvalidOptionException
            assert false;
        } catch (InvalidTransitionException | InvalidInputException | IOException e) {
            assert false;
        } catch (InvalidOptionException e) {
            assert true;
        }

        try{
            fase.runEnemiesTurn();
            // Dado la fase en que esta el contralador, este metodo debe arrojar un InvalidOptionException
            assert false;
        } catch (InvalidTransitionException e) {
            assert false;
        } catch (InvalidOptionException e) {
            assert true;
        }

        try{
            fase.attack(pMarco, 1, AttackType.SALTO);
            // Dado la fase en que esta el contralador, este metodo debe arrojar un InvalidOptionException
            assert false;
        } catch (InvalidOptionException e) {
            assert true;
        } catch (InvalidAttackException e) {
            assert false;
        }

        try{
            fase.useItem(pMarco, 1);
            // Dado la fase en que esta el contralador, este metodo debe arrojar un InvalidOptionException
            assert false;
        } catch (InvalidOptionException e) {
            assert true;
        }
    }

    @Test
    public void invalidTransitionExceptionTest() {
        fase = new UsingItemPhase(pMarco);

        try {
            fase.toAttackingPhase(pMarco);
            assert false;
        } catch (InvalidTransitionException e) {
            assert true;
        }

        try {
            fase.toStartRoundPhase();
            assert false;
        } catch (InvalidTransitionException e) {
            assert true;
        }

        try {
            fase.toChoosingActionPhase();
            assert false;
        } catch (InvalidTransitionException e) {
            assert true;
        }

        try {
            fase.toSetAttackPhase(pMarco);
            assert false;
        } catch (InvalidTransitionException e) {
            assert true;
        }

        try {
            fase.toChoosingItemPhase(pMarco);
            assert false;
        } catch (InvalidTransitionException e) {
            assert true;
        }

        try {
            fase.toEnemiesTurnPhase();
            assert false;
        } catch (InvalidTransitionException e) {
            assert true;
        }

        fase = new StartRoundPhase();

        try {
            fase.toUsingItemPhase(pMarco);
            assert false;
        } catch (InvalidTransitionException e) {
            assert true;
        }

        try {
            fase.toEndRoundPhase();
            assert false;
        } catch (InvalidTransitionException e) {
            assert true;
        }

        try {
            fase.toEndTurnPhase();
            assert false;
        } catch (InvalidTransitionException e) {
            assert true;
        }

    }

    @Test
    public void invalidAttackException(){
        try {
            boo.attack(marco);
            //Debe arrojar un InvalidAttackException
            assert false;
        } catch (InvalidAttackException e) {
            assert true;
        }

        try {
            luis.attack(boo, AttackType.MARTILLO);
            //Debe arrojar un InvalidAttackException
            assert false;
        } catch (InvalidAttackException e) {
            assert true;
        }
    }

    @Test
    public void invalidInputException(){
        pMarco = new PlayerIn(marco, "h\nx\n");
        pMarco.setOut(out);

        try {
            pMarco.selectAction(gameController);
            assert false;
        } catch (IOException e) {
            assert false;
        } catch (InvalidInputException e) {
            assert true;
        }

    }


}
