# ---- Build image ----
FROM node:latest

# change working directory to the app directory
WORKDIR /app

# copy package.json from current filesystem to workingdir
COPY package*.json /app/

# install package.json modules
RUN npm install

# Copy all currently made items into Working directory
COPY ./ /app/

EXPOSE 3000

# build artifact
CMD npm start