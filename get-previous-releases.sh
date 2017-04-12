#! /usr/bin/env sh
DEPLOY_LOCAL_DIR=$1
echo "Prepare deploy local dir = ${DEPLOY_LOCAL_DIR}"


### duplicate these lines for every releases you wish to expose (someone can improve the script by creating a list and iterate ...)
RELEASE_VERSION=1.0.0
# Create nightly folder
mkdir -p $DEPLOY_LOCAL_DIR/$RELEASE_VERSION
cd $DEPLOY_LOCAL_DIR
# Copy update-site to deploy local dir
wget https://github.com/moliz/moliz.xmof/releases/download/release.$RELEASE_VERSION/org.modelexecution.moliz.xmof.updatesite-$RELEASE_VERSION-SNAPSHOT.zip
unzip org.modelexecution.moliz.xmof.updatesite-$RELEASE_VERSION-SNAPSHOT.zip -d $RELEASE_VERSION
rm -rf org.modelexecution.moliz.xmof.updatesite-$RELEASE_VERSION-SNAPSHOT.zip
echo "ls ${DEPLOY_LOCAL_DIR}"
ls $DEPLOY_LOCAL_DIR
