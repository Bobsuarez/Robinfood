{{/*
Expand the name of the chart.
*/}}
{{- define "changestatus.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}
    
{{/*
   Create chart name and version as used by the chart label.
*/}}
{{- define "changestatus.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}
    
{{/*
Create unified labels for changestatus components
*/}}
{{- define "changestatus.common.matchLabels" }}
app: {{ template "changestatus.name" . }}
release: {{ .Release.Name }}
{{- end -}}
    
{{- define "changestatus.common.metaLabels" }}
chart: {{ template "changestatus.chart" . }}
heritage: {{ .Release.Service }}
{{- end -}}
    
{{/*
Common labels
*/}}
{{- define "changestatus.changestatusbc.labels" -}}
helm.sh/chart: {{ include "changestatus.chart" . }}
{{ include "changestatus.changestatusbc.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}
    
{{/*
Common labels
*/}}
{{- define "changestatus.changestatusor.labels" -}}
helm.sh/chart: {{ include "changestatus.chart" . }}
{{ include "changestatus.changestatusor.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}
    
    
{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "changestatus.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}
    
{{/*
Create a default fully qualified app changestatusbc.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "changestatus.changestatusbc.fullname" -}}
{{- if .Values.changestatusbc.fullnameOverride }}
{{- .Values.changestatusbc.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.changestatusbc.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}
    
{{/*
Create a default fully qualified app changestatusor.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "changestatus.changestatusor.fullname" -}}
{{- if .Values.changestatusor.fullnameOverride }}
{{- .Values.changestatusor.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.changestatusor.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}
    
    
{{/*
Create the name of the service account to use
*/}}
{{- define "changestatus.changestatusbc.serviceAccountName" -}}
{{- if .Values.changestatusbc.serviceAccount.create }}
{{- default (include "changestatus.changestatusbc.fullname" .) .Values.changestatusbc.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.changestatusbc.serviceAccount.name }}
{{- end }}
{{- end }}
    
{{/*
Create the name of the service account to use
*/}}
{{- define "changestatus.changestatusor.serviceAccountName" -}}
{{- if .Values.changestatusor.serviceAccount.create }}
{{- default (include "changestatus.changestatusor.fullname" .) .Values.changestatusor.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.changestatusor.serviceAccount.name }}
{{- end }}
{{- end }}
    
{{/*
Selector labels
*/}}
{{- define "changestatus.changestatusbc.selectorLabels" -}}
app.kubernetes.io/name: {{ include "changestatus.changestatusbc.fullname" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}
    
{{/*
Selector labels
*/}}
{{- define "changestatus.changestatusor.selectorLabels" -}}
app.kubernetes.io/name: {{ include "changestatus.changestatusor.fullname" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}
    
{{/*
Define the changestatus.namespace template if set with forceNamespace or .Release.Namespace is set
*/}}
{{- define "changestatus.namespace" }}
{{- if .Values.forceNamespace }}
{{ printf "namespace: %s" .Values.forceNamespace }}
{{- else -}}
{{ printf "namespace: %s" .Release.Namespace }}
{{- end }}
{{- end }}