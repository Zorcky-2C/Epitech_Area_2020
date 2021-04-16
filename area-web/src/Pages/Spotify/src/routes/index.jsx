import React from "react";
import { Route, Switch } from "react-router-dom";

import { PrivateRoute } from '../containers';

import AuthorizeRoute from './AuthorizeRoute';
import DashboardRoute from './DashboardRoute';
import LoginRoute from './LoginRoute';

const Routes = () => {
  return (
    <Switch>
      <Route exact path="/Spotify" component={LoginRoute} />

      <Route exact path="/Spotify/authorize" component={AuthorizeRoute} />

      <PrivateRoute path="/Spotify/dashboard" comp={DashboardRoute} />
    </Switch>
  );
};

export default Routes;
