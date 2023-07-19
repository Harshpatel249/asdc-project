import { Box, Button, Flex, Skeleton, Text } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import CommunityMembers from '../../components/SideBar/CommunityMembers';
import CommunitySideBar from '../../components/SideBar/CommunitySideBar';

function CommunityHome() {
    const choice = 1;
    let { cid } = useParams();
    const [communityDetails, setCommunityDetails] = useState();
    const [loading, setLoading] = useState(true);
    const [interests, setInterests] = useState([]);
    const [members, setMembers] = useState([]);
    const [isMember, setIsMember] = useState(false);
    const userid = 2;


    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const response = await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}`);

                const interestResponse = await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}/interest`);

                const memberResponse = await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}/members`);

                if (response.ok && interestResponse.ok && memberResponse.ok) {
                    const responseData = await response.json();
                    const interestResponseData = await interestResponse.json();
                    const memberResponseData = await memberResponse.json();

                    setMembers(memberResponseData);
                    setCommunityDetails(responseData);
                    setInterests(interestResponseData);

                    members.forEach(function (member) {
                        if (member.user_id === userid) {
                            setIsMember(true);
                        }
                    });

                    setLoading(false);
                }
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [cid]); 

    const handleMemberChange = async () => {
        const postMemberOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ community_id: cid, user_id: userid, user_role: "Member" })
        }

        const deleteMemberOptions = {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ community_id: cid, user_id: userid, user_role: "Member" })
        }

        isMember ?
            await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}/members`, deleteMemberOptions)
            :
            await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}/members`, postMemberOptions);

        setIsMember(!isMember);
    };

    return (
        <Flex>
            <Flex flexGrow="1" justifyContent="center" alignItems="center" h="69vh">
                <CommunitySideBar choice={choice} />
            </Flex>
            <Box flexGrow="6">

                {loading ? <Skeleton /> :
                    <Flex flexDirection="column" justifyContent="start" alignItems="start">
                        <Text fontSize="3xl" fontWeight="medium" mt="16px">Welcome to {communityDetails.name}</Text>
                        <Text fontSize="xl" fontWeight="medium" mt="24px">{communityDetails.description}</Text>
                        <Text fontSize="xl" fontWeight="medium" mt="24px">Community Interests: </Text>
                        <Flex wrap="wrap" w="300px" gap="16px">
                            {interests.map((item, key) => (
                                <Text fontWeight="medium">{item.interestName}</Text>
                            ))}
                        </Flex>
                        {
                            isMember ?
                                <Button onClick={handleMemberChange}>Leave</Button> : <Button onClick={handleMemberChange}>Join</Button>
                        }
                    </Flex>
                }

            </Box>
            <Flex flexGrow="1">
                <CommunityMembers />
            </Flex>
        </Flex>
    );
}

export default CommunityHome;