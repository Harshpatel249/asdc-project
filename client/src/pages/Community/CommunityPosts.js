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
                const response = await fetch("https://commune-dev-csci5308-server.onrender.com/community/posts/");
                const data = await response.json();
                // setCommunityDetails(responseData);
                setPostData(data);
                console.log("postData: ",postData);
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


    return (
        <Flex>
            <Flex flexGrow="1" justifyContent="center" alignItems="center" h="69vh">
                <CommunitySideBar choice={choice} />
               
            </Flex>
            <div>
                <h1>Post Data</h1>
                <ul>
                    {postData.map((post) => (
                    <li key={post.postId}>
                        <h2>{post.postTitle}</h2>
                        <p>{post.description}</p>
                        {/* Render other post data as needed */}
                    </li>
                    ))}
                </ul>
                <SimpleGrid columns={{ base: 1, md: 2, lg: 3 }} spacing={6}>
                    <PostCard
                        key="1"
                        title="Post Title 1"
                        description= "Post Description 1"
                        imageUrl = "data:image/jpeg;base64,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"
                    />
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