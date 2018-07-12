package my.nhorushko.testgeneratorsb.testgeneratorsb.service;

import my.nhorushko.testgeneratorsb.testgeneratorsb.controller.ApplicationController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CmdServiceImplTest {

    @Mock
    private ApplicationController applicationController;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    private CmdService cmdService;

    @Before
    public void setUp() throws Exception {
        cmdService = new CmdServiceImpl(applicationController);
    }

    @Test
    public void run() {

        //given
        final String givenParameter = "en";

        //when
        cmdService.run(givenParameter);

        //then
        verify(applicationController, times(1)).run(stringArgumentCaptor.capture());
        assertEquals(givenParameter, stringArgumentCaptor.getValue());
        verifyNoMoreInteractions(applicationController);
    }
}