import { Flex, Skeleton, Text } from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import CommunityDetails from './CommunityDetails';


function MyCommunity() {

    let { cid } = useParams();
    const [communityList, setCommunityList] = useState([]);
    const [loading, setLoading] = useState(true);
    const userid = localStorage.getItem('userID');
    const [index, setIndex] = useState(0);


    useEffect(() => {
        const fetchData = async () => {
            const getOptions = {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': localStorage.getItem('BearerToken')
                }
            }
            try {
                setLoading(true);
                const response = await fetch(`https://commune-dev-csci5308-server.onrender.com/community/user/${userid}`, getOptions);

                if (response.ok) {
                    const responseData = await response.json();

                    setCommunityList(responseData);

                    setLoading(false);
                }
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [cid]);



    return (
        <Flex justifyContent="space-around">
            <Flex w="40%" flexDirection="column">
                {
                    loading ? <Skeleton></Skeleton> :
                        communityList.map((item, key) => (
                            <button onClick={() => {
                                setIndex(key);
                            }}>
                                <Flex flexDirection="column" w="100%" mt="24px" border="2px" borderColor="black" borderRadius="10px" alignItems="start" p="16px">
                                    <Text fontSize="md">
                                        {item.name}
                                    </Text>
                                    <Text fontSize="md">
                                        {item.description}
                                    </Text>
                                </Flex>
                            </button>
                        ))
                }
            </Flex>
            <Flex w="40%" flexDirection="column">
                {
                    loading ? <Skeleton></Skeleton> :
                        communityList.length === 0 ? null :
                            <CommunityDetails cid={communityList[index].community_id}/>
                }
            </Flex>
        </Flex>

    );
}

export default MyCommunity;