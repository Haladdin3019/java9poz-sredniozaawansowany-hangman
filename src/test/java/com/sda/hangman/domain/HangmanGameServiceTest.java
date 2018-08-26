package com.sda.hangman.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

}