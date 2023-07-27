import { Stack, Text, Box, Card, CardHeader, CardBody, Heading, StackDivider, ListItem, UnorderedList} from "@chakra-ui/react";
import { React, useEffect, useState } from "react";
import { useParams } from 'react-router-dom';
import styles from './event.module.css';
// import { useNavigate } from "react-router-dom";

const EventHome = () => {
    // const userId =2;
    let { eid } = useParams();
    // const userId = localStorage.getItem('userID');
    // const navigate = useNavigate();

    // const [loading, setLoading] = useState(true);
    const [eventDetails, setEventDetails] = useState();
    const [ eventInterests, setEventInterests ] = useState([]);
    const [loading, setLoading] = useState(true);
    // const userid = localStorage.getItem('userID');
    // const handleEditClick = () => {
    //     console.log("event details: "+ eventDetails);
    //     console.log("event interests: "+ eventInterests);
    //     navigate(`/Events/${eventDetails.eventId}/edit-event`, { state: { eventDetails, eventInterests } });
    // }
    
    useEffect(() => {
        const getEventInformation = async () => {
            const getOptions = {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': localStorage.getItem('BearerToken')
                }
            }
            try{
                // console.log("Event id: "+ eid);
                setLoading(true);
                const response = await fetch(`https://commune-dev-csci5308-server.onrender.com/events/${eid}`, getOptions);
                if(response.ok){
                    // console.log("response ok");
                    const responseData = await response.json(); 
                    setEventDetails(responseData);
                }

                const interestResponse = await fetch(`https://commune-dev-csci5308-server.onrender.com/events/${eid}/interests`, getOptions);
                if(interestResponse.ok){
                    const interestResponseData = await interestResponse.json();
                    setEventInterests(interestResponseData);
                    setLoading(false);
                    
                }
            } catch(error){
                console.error(error);
            }
        }
        getEventInformation();
    }
    , [eid]);

    return (
        <>
            {   !loading &&
                <div className={styles.homeContainer}>
                    <Card>
                        <CardHeader>
                            <Heading size='lg'>Event Information</Heading>
                        </CardHeader>

                        <CardBody>
                            <Stack divider={<StackDivider />} spacing='4'>
                            <Box>
                                <Heading size='md' textTransform='uppercase'>
                                    Event Name: 
                                </Heading>
                                <Text pt='2' fontSize='md'>
                                    {eventDetails.eventName}
                                </Text>
                            </Box>
                            <Box>
                                <Heading size='md' textTransform='uppercase'>
                                    Description:
                                </Heading>
                                <Text pt='2' fontSize='md'>
                                    {eventDetails.shortDescription}
                                </Text>
                            </Box>
                            <Box>
                                <Heading size='md' textTransform='uppercase'>
                                    More Information:
                                </Heading>
                                <Text pt='2' fontSize='md'>
                                    {eventDetails.description}
                                </Text>
                            </Box>
                            <Box>
                                <Heading size='md' textTransform='uppercase'>
                                    Location:
                                </Heading>
                                <Text pt='2' fontSize='md'>
                                    {eventDetails.location}
                                </Text>
                            </Box>
                            <Box>
                                <Heading size='md' textTransform='uppercase'>
                                    Start Time:
                                </Heading>
                                <Text pt='2' fontSize='md'>
                                    {eventDetails.eventStartTime}
                                </Text>
                            </Box>
                            <Box>
                                <Heading size='md' textTransform='uppercase'>
                                    End Time:
                                </Heading>
                                <Text pt='2' fontSize='md'>
                                    {eventDetails.eventEndTime}
                                </Text>
                            </Box>
                            <Box>
                                <Heading size='md' textTransform='uppercase'>
                                    Event Type:
                                </Heading>
                                <Text pt='2' fontSize='md'>
                                    {eventDetails.eventType}
                                </Text>
                            </Box>
                            <Box>
                                <Heading size='md' textTransform='uppercase'>
                                    Event Category:
                                </Heading>
                                <UnorderedList>    
                                {   eventInterests.map((interest) => {
                                        return (
                                            <ListItem key={interest.interestId}>
                                                {interest.interestName}
                                            </ListItem>
                                        );
                                    }
                                )}
                                </UnorderedList>   
                            </Box>
                            {/* {   userId === createdByUserId &&
                                <Box>
                                        <Button colorScheme='teal' variant='solid' marginRight="50px" onClick={() => handleEditClick(eventDetails, eventInterests)}>
                                            Edit Event
                                        </Button>
                                    
                                        <Button colorScheme='red' variant='solid'>
                                            Delete Event
                                        </Button>
                                </Box>
                            } */}
                            </Stack>
                        </CardBody>
                    </Card>
            </div> }
        </>
    );
}

export default EventHome;