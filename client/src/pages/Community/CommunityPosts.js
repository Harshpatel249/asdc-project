import { Box, Flex, Skeleton, SimpleGrid, Heading, Text, Image, Button} from '@chakra-ui/react';
import React, { useEffect, useState } from 'react';
// import { useParams } from 'react-router-dom';
import CommunityMembers from '../../components/SideBar/CommunityMembers';
import CommunitySideBar from '../../components/SideBar/CommunitySideBar';

function CommunityPosts() {
    const choice = 2;
    // let { cid } = useParams();
    // const [communityDetails, setCommunityDetails] = useState();
    const [loading, setLoading] = useState(true);
    const [postData, setPostData] = useState([]);
    const userid = 2;


    useEffect(() => {
        const fetchData = async () => {
            try {
                const requestOptions = {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': localStorage.getItem('BearerToken')
                    }
                };
     
            

                const response = await fetch("https://commune-dev-csci5308-server.onrender.com/posts/", requestOptions);
                const data = await response.json();
                // setCommunityDetails(responseData);
                setPostData(data);
                
                setLoading(false);
                
            } catch (error) {
                console.error("Error fetching posts:", error);
                setLoading(false);
            }
        };

        fetchData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []); 

    if (loading) {
        return <div>Loading...</div>;
      }
      const PostCard = ({ title, description, imageUrl }) => (
        <Box p={4} borderWidth="1px" borderRadius="lg" boxShadow="md" marginLeft={"15px"}>
          <Heading as="h3" size="md" mb={2}>
            {title}
          </Heading>
          <Image src={imageUrl} h="400px" objectFit="cover" />
          <Text>{description}</Text>
          <Flex justifyContent="flex-end" p="2">
          <Button backgroundColor={'red'} size="sm" onClick={() => onDelete()}>
          </Button>
      </Flex>
        </Box>
        
      );

      const onDelete = (console.log("deleting"));
      console.log(postData);

    return (
        <Flex>
            <Flex flexGrow="1" justifyContent="center" alignItems="center" h="69vh">
                <CommunitySideBar choice={choice} />
               
            </Flex>
            <div>
                <h1>Post Data</h1>
                <SimpleGrid columns={{ base: 1, md: 2, lg: 3 }} spacing={6}>
                {postData.map((post) => (
                    <PostCard
                        key={post.postId}
                        title={post.postTitle}
                        description= {post.description}
                    />
                    ))}
                </SimpleGrid>
            </div>
            <Box flexGrow="6">

                {loading ? <Skeleton /> :
                    <Flex flexDirection="column" justifyContent="start" alignItems="start">
                        POSTS Page of abc for {userid}
                    </Flex>
                }
            </Box>
            <Flex flexGrow="1">
                <CommunityMembers />
            </Flex>
        </Flex>
    );
}

export default CommunityPosts;