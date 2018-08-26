package com.sda.hangman.domain;

import com.sda.hangman.domain.model.GameStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HangmanGameServiceTest {

    private HangmanGameService hangmanGameService;

    @Before
    public void init() {
        this.hangmanGameService = new HangmanGameService();
    }

    @Test
    public void should_return_array_with_character_indexes() {
        //given
        //when
        List<Integer> result = hangmanGameService.performCharacter('a', "Anna");
        //then
        Assert.assertEquals(2, result.size());
        Assert.assertEquals((Integer) 0, result.get(0));
        Assert.assertEquals((Integer) 3, result.get(1));

    }

    @Test
    public void should_return_empty_array_for_not_existing_char() {
        //given
        //when
        List<Integer> result = hangmanGameService.performCharacter('c', "ANNA");
        //then
        Assert.assertEquals(0, result.size());


    }

    @Test
    public void should_return_array_with_character_indexes_from_phrase_containing_white_space() {
        //given
        //when
        List<Integer> result = hangmanGameService.performCharacter('a', "Ala ma kota");
        //then
        Assert.assertEquals(4, result.size());
        Assert.assertEquals((Integer) 0, result.get(0));
        Assert.assertEquals((Integer) 2, result.get(1));
        Assert.assertEquals((Integer) 5, result.get(2));
        Assert.assertEquals((Integer) 10, result.get(3));

    }

    @Test
    public void processNextLetter_should_update_characterState_when_there_is_letter_in_phrase() {
        //given
        GameStatus gameStatusExpected = new GameStatus("Player", "Anna");
        GameStatus gameStatus = new GameStatus("Player", "Anna");

        //when
        hangmanGameService.processNextLetter('a', gameStatus);
        //then
        Assert.assertNotEquals(gameStatusExpected.getPhraseState(), gameStatus.getPhraseState());
    }

    @Test
    public void processNextLetter_should_not_update_characterState_when_there_is_letter_in_phrase() {
        //given
        GameStatus gameStatusExpected = new GameStatus("Player", "Anna");
        GameStatus gameStatus = new GameStatus("Player", "Anna");

        //when
        hangmanGameService.processNextLetter('v', gameStatus);
        //then
        Assert.assertEquals(gameStatusExpected.getPhraseState(), gameStatus.getPhraseState());

    }

    @Test
    public void processNextLetter_should_update_success_attempts_when_there_is_letter_in_phrase() {
        //given
        GameStatus gameStatus = new GameStatus("Player", "Anna");

        //when
        hangmanGameService.processNextLetter('a', gameStatus);
        //then
        Assert.assertEquals(1, gameStatus.getSuccessAttempts().intValue());
    }

    @Test
    public void processNextLetter_should_update_failed_attempts_when_there_is__not_letter_in_phrase() {
        //given
        GameStatus gameStatus = new GameStatus("Player", "Anna");
        //when
        hangmanGameService.processNextLetter('c', gameStatus);
        //then
        Assert.assertEquals(1, gameStatus.getFailedAttempts().intValue());

    }

    @Test
    public void processNextLetter_should_update_history_for_new_letter() {
        //given
        GameStatus gameStatus = new GameStatus("Player", "Anna");
        //when
        hangmanGameService.processNextLetter('o', gameStatus);
        //then
        Assert.assertEquals(1, gameStatus.getHistory().size());
    }


//    @Test
//    public void processNextLetter_should_not_update_history_for_new_existing_letter() {
//        //given
//        List<Character> history = new ArrayList<>();
//        history.add('a');
//        GameStatus gameStatus = GameStatus.builder()
//                .phrase("Ala ma kota")
//                .phraseState(new Character["Ala ma kota".length()])
//                .history(history)
//                .build();
//        //when
//        hangmanGameService.processNextLetter('a', gameStatus);
//        //then
//        Assert.assertEquals(1, gameStatus.getHistory().size());
//    }
}