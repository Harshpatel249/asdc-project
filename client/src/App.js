import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import {
  ChakraProvider,
  theme
} from '@chakra-ui/react';
import './App.css';
import LayoutWithNav from './pages/Layout/LayoutWithNav';
import LayoutWithoutNav from './pages/Layout/LayoutWithoutNav';
import LandingPage from './pages/LandingPage/LandingPage';
import Onboarding from './pages/OnboardingPage/Onboarding';
import CreateCommunity from './pages/Community/CreateCommunity';
import CommunityHome from './pages/Community/CommunityHome';
import CreateEvent from './pages/Events/CreateEvent';
import CommunityPosts from './pages/Community/CommunityPosts';
import CommunityEvents from './pages/Community/CommunityEvents';
import CommunityAdminHome from './pages/CommunityAdmin/CommunityAdminHome';
import CommunityAdminManageMembers from './pages/CommunityAdmin/CommunityAdminManageMembers';
import CommunityAdminSettings from './pages/CommunityAdmin/CommunityAdminSettings';
import EditCommunity from './pages/CommunityAdmin/EditCommunity';
import MyCommunity from './pages/MyCommunity/MyCommunity';
import { CreateAccount } from './pages/UserAuthentication/CreateAccount/CreateAccount';
import { Reset } from './pages/UserAuthentication/Reset/Reset';
import Login from './pages/UserAuthentication/Login/Login';


const router = createBrowserRouter([
  {
    element: <LayoutWithNav />,
    children: [
      {
        path: "/",
        element: <LandingPage />
      },
      {
        path: "/create-community",
        element: <CreateCommunity />
      },
      {
        path: "/community/:cid/",
        element: <CommunityHome />
      },
      {
        path: "/create-event",
        element: <CreateEvent />
      },
      {
        path: "/community/:cid/posts",
        element: <CommunityPosts />
      },
      {
        path: "/community/:cid/events",
        element: <CommunityEvents />
      },
      {
        path: "/community/:cid/admin/",
        element: <CommunityAdminHome />
      },
      {
        path: "/community/:cid/admin/manage-members",
        element: <CommunityAdminManageMembers />
      },
      {
        path: "/community/:cid/admin/settings",
        element: <CommunityAdminSettings />
      },
      {
        path: "/community/:cid/admin/edit",
        element: <EditCommunity />
      },
      {
        path: "/user/my-community",
        element: <MyCommunity />
      }
    ]
  },
  {
    element: <LayoutWithoutNav />,
    children: [
      {
        path: "/login",
        element: <Login />
      },
      {
        path: "/onboarding",
        element: <Onboarding />
      },
      {
        path: "/signup",
        element: <CreateAccount />
      },
      {
        path: "/reset",
        element: <Reset />
      },
    ]
  }
]);

function App() {
  return (
    <div className="App">
      <ChakraProvider theme={theme}>
        <RouterProvider router={router} />
      </ChakraProvider>
    </div>
  );
}

export default App;
