def parameters = [
                choice(choices: ['apa','apc','is'], description: '应用业务', name: 'APPTYPE'),
                choice(choices: ['test', 'dev', 'uat','staging','live','uatnonlive','livestaging'], description: '环境', name: 'ENVIRONMENT'),
                string(defaultValue: '3', description: '副本数',name: 'REPLICAS'),
                choice(choices: ['th', 'vn', 'id'], description: '国家', name: 'REGION'),

                booleanParam(defaultValue: false, name: "CANARY", description: '是否灰度'),
                booleanParam(defaultValue: false, name: "TAG_RELEASE", description: '是否用 tag 发布。<span style="color: #ed553B; font-weight: bold">正式环境只能使用 tag 发布</span>。'),
                gitParameter(name: 'TAG', type: 'PT_TAG', defaultValue: 'release-nonexist', sortMode: 'DESCENDING_SMART',
                             quickFilterEnabled: true, description: '<span style="color: #ed553B; font-weight: bold">如果正式发布</span>，须采用 tag 进行构建'),
                gitParameter(name: 'BRANCH', type: 'PT_BRANCH', defaultValue: 'deploy', sortMode: 'DESCENDING_SMART',
                             quickFilterEnabled: true, description: '本次构建采用的分支'),
            ]

def propertiesSet(){
    properties([parameters(parameters)])
}

def setCustomEnv(envName, description, defaultValue){
    parameters.add(string(defaultValue: defaultValue, description:description, name: envName))
    properties([parameters(parameters)])
}