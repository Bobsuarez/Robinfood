{{/*
Expand the name of the chart.
*/}}
{{- define "servicesou.name" }}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "servicesou.chart" }}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end -}}

{{/*
Create unified labels for servicesou components
*/}}
{{- define "servicesou.common.matchLabels" }}
app: {{ template "servicesou.name" . }}
release: {{ .Release.Name }}
{{- end -}}

{{- define "servicesou.common.metaLabels" }}
chart: {{ template "servicesou.chart" . }}
heritage: {{ .Release.Service }}
{{- end -}}

{{/*
Common labels
*/}}
{{- define "servicesou.orderbc.labels" -}}
helm.sh/chart: {{ include "servicesou.chart" . }}
{{ include "servicesou.orderbc.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "servicesou.ordercreationqueries.labels" -}}
helm.sh/chart: {{ include "servicesou.chart" . }}
{{ include "servicesou.ordercreationqueries.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{- define "servicesou.orderorlocalserver.labels" -}}
helm.sh/chart: {{ include "servicesou.chart" . }}
{{ include "servicesou.orderorlocalserver.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "servicesou.ordercreation.labels" -}}
helm.sh/chart: {{ include "servicesou.chart" . }}
{{ include "servicesou.ordercreation.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "servicesou.taxesbc.labels" -}}
helm.sh/chart: {{ include "servicesou.chart" . }}
{{ include "servicesou.taxesbc.selectorLabels" . }}
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
{{- define "servicesou.fullname" }}
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
Create a default fully qualified orderbc.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "servicesou.orderbc.fullname" }}
{{- if .Values.orderbc.fullnameOverride }}
{{- .Values.orderbc.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.orderbc.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{- define "servicesou.orderorlocalserver.fullname" }}
{{- if .Values.orderorlocalserver.fullnameOverride }}
{{- .Values.orderorlocalserver.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.orderorlocalserver.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}


{{/*
Create a default fully qualified ordercreation.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "servicesou.ordercreation.fullname" }}
{{- if .Values.ordercreation.fullnameOverride }}
{{- .Values.ordercreation.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.ordercreation.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create a default fully qualified ordercreationqueries.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "servicesou.ordercreationqueries.fullname" }}
{{- if .Values.ordercreationqueries.fullnameOverride }}
{{- .Values.ordercreationqueries.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.ordercreationqueries.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name .Values.ordercreationqueries.name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create a default fully qualified taxesbc.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "servicesou.taxesbc.fullname" }}
{{- if .Values.taxesbc.fullnameOverride }}
{{- .Values.taxesbc.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.taxesbc.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name .Values.taxesbc.name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "servicesou.orderbc.serviceAccountName" }}
{{- if .Values.orderbc.serviceAccount.create }}
{{- default (include "servicesou.orderbc.fullname" .) .Values.orderbc.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.orderbc.serviceAccount.name }}
{{- end }}
{{- end }}

{{- define "servicesou.orderorlocalserver.serviceAccountName" }}
{{- if .Values.orderorlocalserver.serviceAccount.create }}
{{- default (include "servicesou.orderorlocalserver.fullname" .) .Values.orderorlocalserver.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.orderorlocalserver.serviceAccount.name }}
{{- end }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "servicesou.ordercreation.serviceAccountName" }}
{{- if .Values.ordercreation.serviceAccount.create }}
{{- default (include "servicesou.ordercreation.fullname" .) .Values.ordercreation.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.ordercreation.serviceAccount.name }}
{{- end }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "servicesou.ordercreationqueries.serviceAccountName" }}
{{- if .Values.ordercreationqueries.serviceAccount.create }}
{{- default (include "servicesou.ordercreationqueries.fullname" .) .Values.ordercreationqueries.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.ordercreationqueries.serviceAccount.name }}
{{- end }}
{{- end }}


{{/*
Create the name of the service account to use
*/}}
{{- define "servicesou.taxesbc.serviceAccountName" }}
{{- if .Values.taxesbc.serviceAccount.create }}
{{- default (include "servicesou.taxesbc.fullname" .) .Values.taxesbc.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.taxesbc.serviceAccount.name }}
{{- end }}
{{- end }}

{{/*
Define the servicesou.namespace template if set with forceNamespace or .Release.Namespace is set
*/}}
{{- define "servicesou.namespace" }}
{{- if .Values.forceNamespace }}
{{ printf "namespace: %s" .Values.forceNamespace }}
{{- else -}}
{{ printf "namespace: %s" .Release.Namespace }}
{{- end }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "servicesou.orderbc.selectorLabels" -}}
app.kubernetes.io/name: {{ include "servicesou.orderbc.fullname" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "servicesou.orderorlocalserver.selectorLabels" -}}
app.kubernetes.io/name: {{ include "servicesou.orderorlocalserver.fullname" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}


{{/*
Selector labels
*/}}
{{- define "servicesou.ordercreation.selectorLabels" -}}
app.kubernetes.io/name: {{ include "servicesou.ordercreation.fullname" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "servicesou.taxesbc.selectorLabels" -}}
app.kubernetes.io/name: {{ include "servicesou.taxesbc.fullname" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "servicesou.ordercreationqueries.selectorLabels" -}}
app.kubernetes.io/name: {{ include "servicesou.ordercreationqueries.fullname" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}