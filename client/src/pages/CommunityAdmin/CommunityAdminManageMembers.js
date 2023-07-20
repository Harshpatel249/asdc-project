import { Box, Flex, Skeleton, Text, Button } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import CommunityAdminSideBar from '../../components/SideBar/CommunityAdminSideBar';

function CommunityAdminManageMembers() {
    const choice = 2;
    let { cid } = useParams();
    const [communityDetails, setCommunityDetails] = useState();
    const [loading, setLoading] = useState(true);
    const [members, setMembers] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const response = await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}`);
                const memberResponse = await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}/members`);

                if (response.ok && memberResponse.ok) {
                    const responseData = await response.json();
                    const memberResponseData = await memberResponse.json();
                    setCommunityDetails(responseData);
                    setMembers(memberResponseData);
                    setLoading(false);
                }
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [cid]);

    const deleteMember = async (event) => {

        const userid = event.target.value;
        const deleteMemberOptions = {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ community_id: cid, user_id: userid, user_role: "Member" })
        }

        await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}/members`, deleteMemberOptions);

        navigate(`/community/${cid}/admin/`);
      
    };

    const updateMember = async (event) => {

        const userid = event.target.value;
        const updateMemberOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ community_id: cid, user_id: userid, user_role: "Admin" })
        }

        await fetch(`https://commune-dev-csci5308-server.onrender.com/community/${cid}/members?new_role=Admin`, updateMemberOptions);

        navigate(`/community/${cid}/admin/`);
      
    };
    return (
        <Flex>
            <Flex flexGrow="1" justifyContent="center" alignItems="center" h="69vh">
                <CommunityAdminSideBar choice={choice} />
            </Flex>
            <Box flexGrow="6">

                {loading ? <Skeleton /> :
                    <Flex flexDirection="column" justifyContent="start" alignItems="start">
                        <Text fontSize="xl" fontWeight="medium" mt="24px">Members of {communityDetails.name}</Text>
                        {
                            loading ? <Skeleton /> :
                                members.map((item, key) => (
                                    <Flex w="40%" gap="8px" justifyContent="space-between" mt="40px" border="2px" borderColor="black" borderRadius="10px" p="16px">
                                        <Flex flexDirection="column">
                                            <Text fontWeight="medium">Member name: {item.user_name}</Text>
                                            <Text fontWeight="medium">Member role: {item.user_role}</Text>
                                        </Flex>
                                        <Flex flexDirection="column" gap="8px">
                                            {item.user_role === "Admin" ? null : <Button onClick={updateMember} value={item.user_id}>Promote</Button>}
                                            <Button onClick={deleteMember} value={item.user_id}>Remove</Button>
                                        </Flex>
                                    </Flex>
                                ))
                        }
                    </Flex>
                }

            </Box>
        </Flex>
    );
}

export default CommunityAdminManageMembers;